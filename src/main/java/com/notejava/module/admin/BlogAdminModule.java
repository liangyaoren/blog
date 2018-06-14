package com.notejava.module.admin;

import com.google.common.base.Strings;
import com.notejava.bean.Blog;
import com.notejava.bean.BlogType;
import com.notejava.elasticsearch.EsClient;
import com.notejava.lucene.BlogIndex;
import com.notejava.utils.PageUtil;
import com.notejava.utils.ParamsUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IocBean
@At("admin/blog")
public class BlogAdminModule {
    @Inject
    private Dao dao;

    //默认的分页参数
    private static int PAGE_NO = 1;
    private static int PAGE_SIZE = 10;
    BlogIndex blogIndex = new BlogIndex();

    @At("/list")
    @Ok("jsp:admin.blog.list")
    public Object list(Integer pageNo, Integer pageSize) {
        Pager pager = new Pager();
        pager.setPageNumber(ParamsUtil.getInteger(pageNo, PAGE_NO));
        pager.setPageSize(ParamsUtil.getInteger(pageSize, PAGE_SIZE));
        int count = dao.count(Blog.class);
        if (count <= 0) {
            return null;
        }
        List<Blog> blogs = dao.query(Blog.class, Cnd.orderBy().desc("createTime"), pager);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("blogs", blogs);
        resultMap.put("pageBar", PageUtil.getPageBar(pager.getPageNumber(), pager.getPageSize(), count));
        //resultMap.put("pageBar", PageUtil.getPageMap(pager.getPageNumber(), pager.getPageSize(), count));
        return resultMap;
    }

    @At("/info")
    @Ok("jsp:admin.blog.info")
    public Object info(Long id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //查询所有博客类型
        List<BlogType> blogTypes = dao.query(BlogType.class, Cnd.orderBy().desc("orderNo"));
        resultMap.put("blogTypes", blogTypes);
        if (id == null) {
            //添加操作
            return resultMap;
        }
        Blog blog = dao.fetch(Blog.class, id);
        resultMap.put("blog", blog);
        return resultMap;
    }

    @At("/save")
    @Ok("json")
    public Object save(@Param("..") Map<String, Object> map) throws Exception {
        String id = (String) map.get("id");
        String title = ParamsUtil.getString(map.get("title"));
        String content = ParamsUtil.getString(map.get("content"));
        String summary = ParamsUtil.getString(map.get("summary"));
        Long typeId = ParamsUtil.getLong(map.get("typeId"));
        String keyWord = ParamsUtil.getString(map.get("keyWord"));
        String contentNoTag = ParamsUtil.getString(map.get("contentNoTag"));

        Blog blog = null;
        if (Strings.isNullOrEmpty(id)) {
            blog = new Blog();
            blog.setCreateTime(new Date());
        } else {
            blog = dao.fetch(Blog.class, Long.parseLong(id));
        }
        blog.setTitle(title);
        blog.setSummary(summary);
        blog.setContent(content);
        blog.setTypeId(typeId);
        blog.setKeyWord(keyWord);
        blog.setContentNoTag(contentNoTag);

        if (Strings.isNullOrEmpty(id)) {
            //添加
            dao.insert(blog);
            blogIndex.addIndex(blog);
        } else {
            //修改
            dao.update(blog);
            blogIndex.updateIndex(blog);
        }
        //es 索引一份
        EsClient.index(blog);
        return null;
    }

    @At("/delete")
    @Ok("json")
    public Object delete(Long id) throws Exception {
        blogIndex.deleteIndex(String.valueOf(id));
        dao.delete(Blog.class, ParamsUtil.getLong(id));
        return null;
    }
}
