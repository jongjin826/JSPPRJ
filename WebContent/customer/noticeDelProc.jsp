<%@page import="com.tunea.jspprj.dao.jdbc.JdbcNoticeDao"%>
<%@page import="com.tunea.jspprj.dao.NoticeDao"%>
<%@page import="com.tunea.jspprj.model.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String code = request.getParameter("c");

	NoticeDao dao = new JdbcNoticeDao();
	dao.delete(code);
	
	response.sendRedirect("notice.jsp");
%>