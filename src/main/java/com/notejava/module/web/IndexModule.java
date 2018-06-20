package com.notejava.module.web;

import com.google.common.collect.Lists;
import com.notejava.bean.Blog;
import com.notejava.lucene.BlogIndex;
import com.notejava.utils.PageUtil;
import com.notejava.utils.ParamsUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IocBean
public class IndexModule {
    private static Logger logger = Logger.getLogger(IndexModule.class);
    @Inject
    private Dao dao;

    //默认的分页参数
    private static int PAGE_NO = 1;
    private static int PAGE_SIZE = 10;

    BlogIndex blogIndex = new BlogIndex();
    private ServletContext servletContext = Mvcs.getServletContext();

    @At("/index")
    @Ok("jsp:web.blog.list")
    public Object index(@Param("..") Map<String, Object> map) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        Pager pager = new Pager();
        pager.setPageNumber(ParamsUtil.getInteger(map.get("pageNo"), PAGE_NO));
        pager.setPageSize(ParamsUtil.getInteger(map.get("pageSize"), PAGE_SIZE));

        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("createTime");

        String createTimeCnd = ParamsUtil.getString(map.get("createTime"), null);
        if (createTimeCnd != null) {
            resultMap.put("createTime", createTimeCnd);
            Long createTimeStart = DateUtils.parseDate(createTimeCnd + "-1", "yyyy-MM-dd").getTime();
            Long createTimeEnd = DateUtils.parseDate(createTimeCnd + "-31", "yyyy-MM-dd").getTime();
            cri.where().andGTE("createTime", createTimeStart);
            cri.where().andLTE("createTime", createTimeEnd);
        }

        Long typeIdCnd = ParamsUtil.getLong(map.get("typeId"), null);
        if (typeIdCnd != null) {
            cri.where().and("typeId", "=", typeIdCnd);
            resultMap.put("typeId", typeIdCnd);
        }

        int count = dao.count(Blog.class, cri);
        if (count <= 0) {
            return null;
        }

        List<Blog> blogList = dao.query(Blog.class, cri, pager);
        List<Map<String, Object>> items = new ArrayList<>();
        for (Blog blog : blogList) {
            Map<String, Object> blogMap = new HashMap<>();
            String content = blog.getContent();
            Document doc = Jsoup.parse(content);
            Elements imgElements = doc.select("img");
            List<String> imgUrls = Lists.newArrayList();
            if (!imgElements.isEmpty()) {
                for (Element element : imgElements) {
                    String imgUrl = element.attr("src");
                    if (imgUrls.size() < 2){
                        imgUrls.add(imgUrl);
                    }
                }
            }
            blogMap.put("imgUrls", imgUrls);
            blogMap.put("id", blog.getId());
            blogMap.put("title", blog.getTitle());
            blogMap.put("summary", blog.getSummary());
            blogMap.put("createTime", DateFormatUtils.format(blog.getCreateTime(), "yyyy-MM-dd"));
            blogMap.put("clickHit", blog.getClicks());
            blogMap.put("replyHit", blog.getReplys());
            blogMap.put("typeId", blog.getTypeId());
            String typeName = ((Map<Long, String>) servletContext.getAttribute("blogTypes")).get(blog.getTypeId());
            blogMap.put("typeName", typeName);
            items.add(blogMap);
        }

        resultMap.put("blogList", items);
        resultMap.put("pageBar", PageUtil.getPageMap(pager.getPageNumber(), pager.getPageSize(), count));
        return resultMap;
    }

    @At("/search")
    @Ok("jsp:web.blog.result")
    public Object search(@Param("q") String q, Integer pageNo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("q", q);
        q = ParamsUtil.getString(q);
        pageNo = ParamsUtil.getInteger(pageNo, 1);
        try {
            Map<String, Object> respMap = blogIndex.search(q, pageNo);
            int count = (int) respMap.get("count");
            if (count > 0) {
                Map<String, Integer> pageBar = PageUtil.getPageMap(pageNo, 10, count);
                resultMap.put("pageBar", pageBar);
            }

            resultMap.putAll(respMap);
        } catch (Exception e) {
            logger.error("lucene搜索失败" + e.getMessage(), e);
        }
        return resultMap;
    }

    @At("/blogger/info")
    @Ok("jsp:web.blogger.info")
    public Object aboutBlogger() {
        return null;
    }

}
