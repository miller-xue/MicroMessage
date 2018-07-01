package com.miller.util;

/**
 * ��Ŀ������
 * @author Miller
 *
 */
public class MessageUtils {
	/**
	 * �رն���
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
	 * �ж��ַ����Ƿ�Ϊ��
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
