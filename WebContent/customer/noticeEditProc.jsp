<%@page import="com.tunea.jspprj.dao.jdbc.JdbcNoticeDao"%>
<%@page import="com.tunea.jspprj.dao.NoticeDao"%>
<%@page import="com.tunea.jspprj.model.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String title = request.getParameter("title");
	String code = request.getParameter("code");
	String content = request.getParameter("content");
	
	Notice n = new Notice();
	n.setTitle(title);
	n.setContent(content);
	n.setCode(code);
	
	NoticeDao dao = new JdbcNoticeDao();
	dao.update(n);
	
	response.sendRedirect("noticeDetail.jsp?c="+code);
%>