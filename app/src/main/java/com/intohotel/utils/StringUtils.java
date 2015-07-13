package com.intohotel.utils;

/**
 * @ClassName StringUtils
 * @Description 字条操作工作具类
 * @author jinyb
 * @date 2015-1-4
 */
public class StringUtils {
	
	/** 
	 * @Method: getStringLength 
	 * @Description: 获得指定长度的string
	 * @param @param source
	 * @param @param length
	 * @param @return
	 * @return String
	 * @throws 
	 */
	public static String getStringLength(String source,int length){
//		String str = "";
		if(source == null ){
			return "";
		}
		//过滤掉所有的换行。
		source = source.replace("\n", "");
		if(source.length() <= length){
			return source;
		}else{
			String str = source.substring(0, length) +"...";
			return str;
		}
	}

}
