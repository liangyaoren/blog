package com.notejava.filters;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;

public class ModuleFilter implements MethodInterceptor {
	private static Logger logger = Logger.getLogger(MethodInterceptor.class);

	@Override
	public void filter(InterceptorChain chain) throws Throwable {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			chain.doChain();
			resultMap.put("code", 0);
			resultMap.put("data", chain.getReturn());
		} catch (Exception e) {
			resultMap.put("code", -1);
			resultMap.put("errorMsg", e.toString());
			logger.error(e.toString(),e);
		}
		chain.setReturnValue(resultMap);
	}
}
