package com.notejava.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Global {
	private Global() {}

	private static Global global = new Global();

	private static Map<String, String> map = Maps.newHashMap();

	private static Properties properties = new Properties();

	public static Global getInstance() {
		return global;
	}

	public static void init() {
		try {
			properties.load(Global.class.getClassLoader().getResourceAsStream("args.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = properties.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
}
