package com.notejava.module.admin;

import com.notejava.bean.Blogger;
import com.notejava.refresh.Refresher;
import com.notejava.utils.ParamsUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import java.util.Map;

@IocBean
@At("admin")
public class AdminModule {
    @Inject
    private Dao dao;

    @At("/index")
    @Ok("jsp:admin.index")
    public Object index() {
        return null;
    }

    @At("/home")
    @Ok("jsp:admin.home")
    public Object home() {
        return null;
    }

    @At("/flush")
    @Ok("json")
    public Object flush() {
        Refresher.getInstance().refresh();
        return null;
    }

    @At("/modifyPwd")
    @Ok("json")
    public Object modifyPwd(@Param("..") Map<String, Object> map) {
        String userName = ParamsUtil.getString(map.get("userName"));
        String password = ParamsUtil.getString(map.get("password"));
        String newPassword = ParamsUtil.getString(map.get("newPassword"));

        Blogger user = dao.fetch(Blogger.class, Cnd.where("userName", "=", userName).and("password", "=", password));
        if (user == null) {
            throw new RuntimeException("原用户或密码错误");
        }

        user.setPassword(newPassword);
        dao.update(user);
        return null;
    }
}
