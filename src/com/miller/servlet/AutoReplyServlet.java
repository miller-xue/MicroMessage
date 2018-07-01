package com.miller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miller.service.QueryService;

/**
 * @author Miller
 * 自动回复控制层
 */
@SuppressWarnings("serial")
public class AutoReplyServlet extends HttpServlet {

	private QueryService queryService = new QueryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String content = req.getParameter("content");
		PrintWriter out = resp.getWriter();
		out.write(queryService.queryByCommand(content));
		out.flush();
		out.close();
	}


}
