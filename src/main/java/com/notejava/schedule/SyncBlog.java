package com.notejava.schedule;

import com.notejava.bean.Blog;
import com.notejava.bean.Comment;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by yaoren on 2017/4/23.
 */
public class SyncBlog extends TimerTask {

    @Override
    public void run() {

        Ioc ioc = Mvcs.ctx().getDefaultIoc();
        Dao dao = ioc.get(Dao.class);

        Pager pager = new Pager().setPageNumber(1).setPageSize(5);
        ServletContext servletContext = Mvcs.getServletContext();

        List<Blog> hotBlog = dao.query(Blog.class, Cnd.orderBy().desc("clicks"), pager);
        servletContext.setAttribute("hotBlog", hotBlog);

        List<Blog> newBlog = dao.query(Blog.class, Cnd.orderBy().desc("createTime"), pager);
        servletContext.setAttribute("newBlog", newBlog);

        List<Comment> comments = dao.query(Comment.class, Cnd.where("state", "=", 1).desc("createTime"), pager);
        servletContext.setAttribute("newComments", comments);

    }
}
