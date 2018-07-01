package com.miller.servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miller.bean.Message;
import com.miller.entity.Page;
import com.miller.service.ListService;

@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

	private ListService service = new ListService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���ñ���		
		req.setCharacterEncoding("UTF-8");
		// ��ȡ����
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		
		// ��ǰҳ
		String currentPage = req.getParameter("currentPage");
		
		//������ҳ����
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		}else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		
		// ��ҳ�洫ֵ
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		// ��ѯ���ݿ�
		List<Message> messageList = service.queryMessageListByPage(command, description,page);
		req.setAttribute("page", page);
		req.setAttribute("messageList", messageList);
		// �ַ�ת��
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
