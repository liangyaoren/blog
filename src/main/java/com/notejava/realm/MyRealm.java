package com.notejava.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;

import com.notejava.bean.Blogger;

public class MyRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		Ioc ioc = Mvcs.getIoc();
        Dao dao = ioc.get(Dao.class);
		Blogger blogger = dao.fetch(Blogger.class, Cnd.where("userName", "=", userName));
		if(blogger!=null){
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,blogger.getPassword(),"myRealm");
			return authenticationInfo;
		}
		return null;
	}

}
