package com.notejava.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.Strings;

/**
 * 参数工具类
 * @author yaoren
 *
 */
public class ParamsUtil {
	
	private static final String DEF_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String getString(Object obj){
		String value = paserString(obj);
		if(Strings.isNullOrEmpty(value)){
			throw new RuntimeException("参数不能为空");
		}
		return value;
	}
	
	public static String getString(Object obj,String def){
		String value = paserString(obj);
		if(Strings.isNullOrEmpty(value)){
			return def;
		}
		return value;
	}
	
	private static String paserString(Object obj) {
		String value = null;
		if(obj!=null){
			if(obj instanceof String){
				value = obj.toString().trim();
				if(value.isEmpty()){
					value = null;
				}
			}else if(obj instanceof Date) {
				value = new SimpleDateFormat(DEF_DATE_FORMAT).format(obj);
			}else if(obj instanceof Integer){
				value = String.valueOf(obj);
			}else {
				value = obj.toString().trim();
				if(value.isEmpty()) {
					value = null;
				}
			}
		}
		return value;
	}

	public static Integer getInteger(Object obj){
		Integer value = paserInteger(obj);
		if(value==null){
			throw new RuntimeException("参数不能为空");
		}
		return value;
	}
	
	public static Integer getInteger(Object obj,Integer def){
		Integer value = paserInteger(obj);
		if(value==null){
			return def;
		}
		return value;
	}
	
	private static Integer paserInteger(Object obj) {
		Integer value = null;
		if(obj!=null){
			if(obj instanceof Integer) {
				value = (Integer)obj;
			} else if(obj instanceof String) {
				if(!obj.toString().trim().isEmpty()) {
					value = Integer.parseInt(obj.toString().trim());
				}
			}else {
				throw new RuntimeException("不能转为Integer类型");
			}
		}
		return value;
	}
	
	public static Long getLong(Object obj){
		Long value = paserLong(obj);
		if(value == null){
			throw new RuntimeException("参数不能为空");
		}
		return value;
	}
	
	public static Long getLong(Object obj,Long def){
		Long value = paserLong(obj);
		if(value == null){
			return def;
		}
		return value;
	}
	
	private static Long paserLong(Object obj) {
		Long value = null;
		if(obj!=null){
			if(obj instanceof Long){
				value = (Long)obj;
			}else if(obj instanceof String) {
				if(!obj.toString().trim().isEmpty()) {
					value = Long.parseLong(obj.toString().trim());
				}
			}else if(obj instanceof Integer) {
				value = ((Number)obj).longValue();
			}else{
				throw new RuntimeException("参数不能转为Long类型");
			}
		}
		return value;
	}
	
	public static Float getFloat(Object obj){
		Float value = paserFloat(obj);
		if(value == null){
			throw new RuntimeException("参数不能为空");
		}
		return value;
	}
	
	public static Float getFloat(Object obj,Float def){
		Float value = paserFloat(obj);
		if(value == null){
			return def;
		}
		return value;
	}

	private static Float paserFloat(Object obj) {
		Float value = null;
		if(obj!=null){
			if(obj instanceof Float){
				value = (Float)obj;
			}else if(obj instanceof String){
				if(!obj.toString().trim().isEmpty()){
					value = Float.parseFloat(obj.toString().trim());
				}
			}else if(obj instanceof Integer || obj instanceof Long){
				value = ((Number)obj).floatValue();
			}else{
				throw new RuntimeException("参数不能转为Float类型");
			}
		}
		return value;
	}

	public static Double getDouble(Object obj){
		Double value = paserDouble(obj);
		if(value == null){
			throw new RuntimeException("参数不能为空");
		}
		return value;
	}
	
	public static Double getDouble(Object obj,Double def){
		Double value = paserDouble(obj);
		if(value == null){
			return def;
		}
		return value;
	}

	private static Double paserDouble(Object obj) {
		Double value = null;
		if(obj!=null){
			if(obj instanceof Double){
				value = (Double)obj;
			}else if(obj instanceof String){
				if(!obj.toString().trim().isEmpty()){
					value = Double.parseDouble(obj.toString().trim());
				}
			}else if(obj instanceof Integer || obj instanceof Long || obj instanceof Float){
				value = ((Number)obj).doubleValue();
			}else{
				throw new RuntimeException("参数不能转为Double类型");
			}
		}
		return value;
	}
}
