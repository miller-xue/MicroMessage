package com.miller.util;

/**
 * 项目工具类
 * @author Miller
 *
 */
public class MessageUtils {
	/**
	 * 关闭对象
	 * @param close
	 */
	public static void close(AutoCloseable close) {
		if(close != null) {
			try {
				close.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断字符串是否为空
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if(string == null || string.trim().equals("")) {
			return true;
		}
		return false;
	}
}
