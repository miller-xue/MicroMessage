package com.miller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miller.service.CommandService;

@WebServlet(value="/command/save")
public class CommandSave extends HttpServlet {
	
	private CommandService service = new CommandService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String[] contents = req.getParameterValues("content");
		
		service.save(name, description, contents);
		resp.sendRedirect(req.getContextPath() + "/List");
	}
	
}
