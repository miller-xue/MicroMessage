package com.miller.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.miller.dao.MessageDao;
import com.miller.util.MessageUtils;

/**
 * @author Miller
 * ά����ص�ҵ����
 */
public class MaintinService {
	
	private MessageDao messageDao = new MessageDao();
	
	
	public void deleteById(String id) throws Exception {
		if(MessageUtils.isEmpty(id)) {
			throw new Exception("��������Ϊ��");
		}
		
		int result = messageDao.deleteById(Integer.valueOf(id));
		if(result <= 0) {
			throw new Exception("ɾ��ʧ��");
		}
	}
	
	
	public void deleteBatch(String[] ids) throws Exception {
		if(ids == null || ids.length == 0) {
			throw new Exception("��������Ϊ��");
		}
		List<Integer> idList = new ArrayList<>();
		for (String str : ids) {
			idList.add(Integer.valueOf(str));
		}
		int result = messageDao.deleteBatch(idList);
		if(result <= 0) {
			throw new Exception("ɾ��ʧ��");
		}
	}
}
