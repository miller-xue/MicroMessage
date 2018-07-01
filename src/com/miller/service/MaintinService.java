package com.miller.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.miller.dao.MessageDao;
import com.miller.util.MessageUtils;

/**
 * @author Miller
 * 维护相关的业务功能
 */
public class MaintinService {
	
	private MessageDao messageDao = new MessageDao();
	
	
	public void deleteById(String id) throws Exception {
		if(MessageUtils.isEmpty(id)) {
			throw new Exception("参数不能为空");
		}
		
		int result = messageDao.deleteById(Integer.valueOf(id));
		if(result <= 0) {
			throw new Exception("删除失败");
		}
	}
	
	
	public void deleteBatch(String[] ids) throws Exception {
		if(ids == null || ids.length == 0) {
			throw new Exception("参数不能为空");
		}
		List<Integer> idList = new ArrayList<>();
		for (String str : ids) {
			idList.add(Integer.valueOf(str));
		}
		int result = messageDao.deleteBatch(idList);
		if(result <= 0) {
			throw new Exception("删除失败");
		}
	}
}
