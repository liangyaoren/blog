package com.notejava.elasticsearch;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.notejava.bean.Blog;
import com.notejava.utils.Global;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lyle 2018/6/13 11:10.
 */
public class EsClient {
    private static TransportClient client;
    private static final String INDEX = "notejava";
    private static final String TYPE = "blog";
    private static final Log LOG = LogFactory.getLog(EsClient.class);

    public static void init() {
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(Global.getConfig("es.ip")), Integer.valueOf(Global.getConfig("es.port"))));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据博客 id 查询 es 文档
     *
     * @param id
     * @return
     */
    public static SearchHit getHitById(Long id) {
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("id", id);
        SearchResponse sr = client.prepareSearch(INDEX).setQuery(queryBuilder).get();
        SearchHit[] hits = sr.getHits().getHits();
        if (ArrayUtils.isEmpty(hits)) {
            return null;
        }
        return hits[0];
    }

    /**
     * 索引博客
     *
     * @param blog
     */
    public static void index(Blog blog) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", blog.getId());
        map.put("title", blog.getTitle());
        String content = StringUtils.isEmpty(blog.getContentNoTag()) ? StringEscapeUtils.escapeHtml4(blog.getContent()) : blog.getContentNoTag();
        map.put("content", content);
        map.put("createTime", DateFormatUtils.format(blog.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        SearchHit hit = getHitById(blog.getId());
        if (hit == null) {
            client.prepareIndex(INDEX, TYPE).setSource(map).get();
        } else {
            hit.getSourceAsMap().putAll(map);
            client.prepareIndex(INDEX, TYPE, hit.getId()).setSource(hit.getSourceAsMap()).get();
        }
    }

    /**
     * 分页查找文档
     *
     * @param q 关键字
     */
    public static Map<String, Object> find(String q, Integer pageNo) {
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(q, "content", "title");
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("content").field("title").preTags("<b><font color='red'>").postTags("</font></b>");
        int size = 10;
        int from = (pageNo - 1) * size;
        SearchResponse sr = client.prepareSearch(INDEX).setQuery(queryBuilder).highlighter(highlightBuilder).setFrom(from).setSize(size).get();
        SearchHits hits = sr.getHits();
        long count = hits.getTotalHits();
        List<SearchHit> hitList = Lists.newArrayList(hits);
        List<Map<String, Object>> collect = hitList
                .stream()
                .map(h -> {
                    Map<String, Object> sourceAsMap = h.getSourceAsMap();
                    List<HighlightField> highlightFields = Lists.newArrayList(h.getHighlightFields().values());
                    for (HighlightField highlightField : highlightFields) {
                        List<Text> texts = Lists.newArrayList(highlightField.getFragments());
                        StringBuilder highlightStr = new StringBuilder("");
                        for (Text text : texts) {
                            if (highlightStr.length() < 200){
                                highlightStr.append(text.string());
                            }
                        }
                        sourceAsMap.put(highlightField.getName(), highlightStr.toString());
                    }
                    return sourceAsMap;
                }).collect(Collectors.toList());
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("count", count);
        result.put("blogs", collect);
        return result;
    }
}
