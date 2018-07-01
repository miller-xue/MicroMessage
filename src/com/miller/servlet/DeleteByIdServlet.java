package com.miller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miller.service.MaintinService;

/**
 * @author Miller
 *
 */
public class DeleteByIdServlet extends HttpServlet{

	private MaintinService service = new MaintinService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���ñ���		
		req.setCharacterEncoding("UTF-8");
		// ��ȡ����
		String id = req.getParameter("id");
		// ��ѯ���ݿ�
		try {
			service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ַ�ת��
		resp.sendRedirect(req.getContextPath() + "/List");
//		req.getRequestDispatcher("/List").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
