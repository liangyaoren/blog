package com.notejava.lucene;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.google.common.base.Strings;
import com.notejava.bean.Blog;

public class BlogIndex {
	private static Path path = Paths.get("/data/lucene");
	private static  Analyzer analyzer = new SmartChineseAnalyzer();

	private IndexWriter getWriter() throws Exception{
		Directory dir = FSDirectory.open(path);
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, config);
		return writer;
	}

	public void addIndex(Blog blog) throws Exception{
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
		doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
		doc.add(new StringField("releaseDate", DateFormatUtils.format(blog.getCreateTime(), "yyyy-MM-dd"), Field.Store.YES));
		doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
		IndexWriter writer = getWriter();
		writer.addDocument(doc);
		writer.close();
	}

	public void updateIndex(Blog blog)throws Exception{
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
		doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
		doc.add(new StringField("releaseDate", DateFormatUtils.format(blog.getCreateTime(), "yyyy-MM-dd"), Field.Store.YES));
		doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
		//实际先删除再插入
		IndexWriter writer = getWriter();
		writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
		writer.close();
	}

	public void deleteIndex(String blogId) throws Exception{
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term("id", blogId));
		writer.forceMergeDeletes();
		writer.commit();
		writer.close();
	}

	public Map<String,Object> search(String q,Integer pageNo)throws Exception{
		Directory dir = FSDirectory.open(path);
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is=new IndexSearcher(reader);

		//创建搜索条件
		Builder builder = new Builder();
		//根据关键字对title字段进行查询
		Query titleQuery = new QueryParser("title", analyzer).parse(q);
		//根据关键字对content字段进行查询
		Query contentQuery = new QueryParser("content", analyzer).parse(q);
		builder.add(titleQuery,Occur.SHOULD);
		builder.add(contentQuery,Occur.SHOULD);
		BooleanQuery query = builder.build();
		
		int count = is.count(query);
		
		//获取最后一个文档的id
		ScoreDoc lastSd = getLastScoreDoc(pageNo, 10, query, is);

		//表示命中返回
		TopDocs topDocs = is.searchAfter(lastSd, query, 10);

		QueryScorer scorer = new QueryScorer(titleQuery);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer); 

		SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		Highlighter highLighter = new Highlighter(simpleHTMLFormatter,scorer);
		highLighter.setTextFragmenter(fragmenter);

		//得到击中
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		List<Blog> blogs = new LinkedList<Blog>();
		for(ScoreDoc scoreDoc:scoreDocs){
			Blog blog = new Blog();
			Document doc = is.doc(scoreDoc.doc);
			String id = doc.get("id");
			blog.setId(Long.parseLong(id));
			String createTimeStr = doc.get("releaseDate");
			blog.setcreateTimeStr(createTimeStr);
			String title = doc.get("title");
			String content=StringEscapeUtils.escapeHtml4(doc.get("content"));
			if(title!=null){
				TokenStream tokenStream = analyzer.tokenStream("title", title);
				String htitle = highLighter.getBestFragment(tokenStream, title);
				if(Strings.isNullOrEmpty(htitle)){
					htitle = title;
				}
				blog.setTitle(htitle);
			}

			if(content!=null){
				TokenStream tokenStream = analyzer.tokenStream("content", content);
				String hcontent = highLighter.getBestFragment(tokenStream, content);
				if(Strings.isNullOrEmpty(hcontent)){
					if(content.length()<200){
						hcontent = content;
					}else{
						hcontent = content.substring(0, 200);
					}
				}
				blog.setContent(hcontent);
			}
			blogs.add(blog);
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blogs", blogs);
		resultMap.put("count", count);
		return resultMap;
	}

	private ScoreDoc getLastScoreDoc(Integer pageNo, int pageSize, Query query, IndexSearcher is) throws IOException {
		if(pageNo==1)
			return null;//如果是第一页就返回空  
	    int num = pageSize*(pageNo-1);//获取上一页的最后数量  
	    TopDocs tds = is.search(query, num);  
	    return tds.scoreDocs[num-1];  
	}
}
