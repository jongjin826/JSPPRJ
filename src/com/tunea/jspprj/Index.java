package com.tunea.jspprj;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class Index extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		ServletContext application = reqest.getServletContext();
		HttpSession session = reqest.getSession();
		Cookie[] cookies = reqest.getCookies();
		
		String _sumApp = "0";
		String _sumSession = "0";
		String _sumCookie = "0";
		
		if (application.getAttribute("sum") != null)
			_sumApp = application.getAttribute("sum").toString();

		if (session.getAttribute("sum") != null)
			_sumSession = session.getAttribute("sum").toString();
		
		if(cookies != null)
			for (Cookie cookie : cookies)
				if(cookie.getName().equals("sum"))
					_sumCookie = cookie.getValue();
		
		PrintWriter pw = response.getWriter();

		pw.write("<!DOCTYPE html>");
		pw.write("<html>");
		pw.write("<head>");
		pw.write("<meta charset=\"EUC-KR\">");
		pw.write("<title>HOME</title>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("			<p><a href=\"add\">계산하기</a></p>");
		pw.write("			<p>마지막 계산값 app: " + _sumApp + "</p> ");
		pw.write("			<p>마지막 계산값 session: " + _sumSession + "</p> ");
		pw.write("			<p>마지막 계산값 cookie: " + _sumCookie + "</p> ");
		pw.write("</body>");
		pw.write("</html>");
	}
}
