package com.notejava.module.callback;

import com.alibaba.druid.support.json.JSONUtils;
import com.notejava.bean.Comment;
import com.notejava.utils.ParamsUtil;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoren on 2017/5/24.
 */
@IocBean
@At("comment")
public class CommentModule {
    @Inject
    private Dao dao;

    @At("/callback")
    public void callback(@Param("data") String data){
        Map<String,Object> map = (Map<String, Object>) JSONUtils.parse(ParamsUtil.getString(data));
        List<Map<String,Object>> comments = (List<Map<String, Object>>) map.get("comments");

        Long blogId = Long.valueOf(map.get("sourceid").toString());

        for (Map<String,Object> comment : comments){
            String content = (String) comment.get("content");
            String ip = (String) comment.get("ip");
            Long time = (Long) comment.get("ctime");
            Map<String,Object> user = (Map<String, Object>)comment.get("user");
            String nickname = (String)user.get("nickname");

            Comment comm = new Comment();
            comm.setCreateTime(new Date(time));
            comm.setBlogId(blogId);
            comm.setContent(content);
            comm.setUserIp(ip);
            comm.setNickname(nickname);
            comm.setState(1);

            dao.insert(comm);
        }
    }
}
