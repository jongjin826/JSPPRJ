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

@WebServlet("/add")
public class Calc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter pw = response.getWriter();
		int x = 0;
		int y = 0;
		if (request.getMethod().equals("POST")) {
			String _x = request.getParameter("x");
			String _y = request.getParameter("y");

			if (_x != null && !_x.equals(""))
				x = Integer.parseInt(_x);
			if (_y != null && !_y.equals(""))
				y = Integer.parseInt(_y);
		}

		String _save = request.getParameter("save");
		if (_save != null) {
			String _sum = request.getParameter("sum");
			//_save = new String(_save.getBytes("ISO-8859-1"),"UTF-8"); //^^
			System.out.println(_save);
			
			if (_save.equals("¾Û")) {
				ServletContext application = request.getServletContext();
				application.setAttribute("sum", _sum);
				System.out.println("saved in app");
			}
			
			if (_save.equals("¼¼¼Ç")) {
				HttpSession session = request.getSession();
				session.setAttribute("sum", _sum);
				System.out.println("saved in session");
			}
			
			if(_save.equals("ÄíÅ°")) {
				Cookie cookie = new Cookie("sum", _sum);
				cookie.setMaxAge(24*60*60);//24½Ã°£
				response.addCookie(cookie);
				System.out.println("saved in cookie");
			}
		}

		int sum = x + y;

		pw.write("<!DOCTYPE html>");
		pw.write("<html>");
		pw.write("<head>");
		pw.write("<meta charset=\"EUC-KR\">");
		pw.write("<title>°è»ê°á°ú</title>");
		pw.write("</head>");
		pw.write("<body>");
		pw.write("	<form action=\"add\" method=\"post\">");
		pw.write("		<ul>");
		pw.write("			<li><label for=\"x\">X: </label><input name=\"x\" /></li>");
		pw.write("			<li><label for=\"y\">Y: </label><input name=\"y\" /></li>");
		pw.write("			<li"
				+ "><label for=\"Sum\">Sum: </label><input name=\"sum\" value=\""
				+ sum + "\" /></li>");
		pw.write("		</ul>");
		pw.write("		<p>");
		pw.write("			<input type=\"submit\" value=\"µ¡¼À\">");
		pw.write("			<input type=\"submit\" name=\"save\" value=\"¾Û\">");
		pw.write("			<input type=\"submit\" name=\"save\" value=\"¼¼¼Ç\">");
		pw.write("			<input type=\"submit\" name=\"save\" value=\"ÄíÅ°\"></form>");
		pw.write("			<a href=\"home\">home</a>");
		pw.write("</body>");
		pw.write("</html>");
	}

	/*
	 * @Override protected void doPost(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * response.setContentType("text/html;charset=UTF-8");
	 * 
	 * PrintWriter pw = response.getWriter();
	 * 
	 * String _x = request.getParameter("x"); String _y =
	 * request.getParameter("x");
	 * 
	 * int x = 0; int y = 0;
	 * 
	 * if (!_x.equals("") && _x != null) x =
	 * Integer.parseInt(request.getParameter("x")); if (!_y.equals("") && _y !=
	 * null) y = Integer.parseInt(request.getParameter("y"));
	 * 
	 * int sum = x + y;
	 * 
	 * pw.write("<!DOCTYPE html>"); pw.write("<html>"); pw.write("<head>");
	 * pw.write("<meta charset=\"EUC-KR\">"); pw.write("<title>È£·Ñ·Ñ·Î</title>");
	 * pw.write("</head>"); pw.write("<body>");
	 * pw.write("	<form action=\"add\" method=\"post\">"); pw.write("		<ul>");
	 * pw.write("			<li><label for=\"x\">X: </label><input name=\"x\" /></li>");
	 * pw.write("			<li><label for=\"y\">Y: </label><input name=\"y\" /></li>");
	 * pw.write("			<li><label for=\"Sum\">Sum: </label><input value=\"" + sum +
	 * "\" /></li>"); pw.write("		</ul>"); pw.write("		<p>");
	 * pw.write("			<input type=\"submit\" value=\"µ¡¼À\"></form>");
	 * pw.write("</body>"); pw.write("</html>"); }
	 */
}
