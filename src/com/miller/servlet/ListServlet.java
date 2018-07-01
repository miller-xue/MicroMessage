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
		// 设置编码		
		req.setCharacterEncoding("UTF-8");
		// 获取参数
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		
		// 当前页
		String currentPage = req.getParameter("currentPage");
		
		//创建分页对象
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		}else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		
		// 向页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		// 查询数据库
		List<Message> messageList = service.queryMessageListByPage(command, description,page);
		req.setAttribute("page", page);
		req.setAttribute("messageList", messageList);
		// 分发转向
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
