<%@page import="com.tunea.jspprj.dao.jdbc.JdbcNoticeFileDao"%>
<%@page import="com.tunea.jspprj.dao.NoticeFileDao"%>
<%@page import="com.tunea.jspprj.model.NoticeFile"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.tunea.jspprj.dao.jdbc.JdbcNoticeDao"%>
<%@page import="com.tunea.jspprj.dao.NoticeDao"%>
<%@page import="com.tunea.jspprj.model.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ServletContext ctx = request.getServletContext();
	String path = ctx.getRealPath("/customer/upload");
	out.println(path + "<br />");

	MultipartRequest req = new MultipartRequest(request, path,
			1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());

	String title = req.getParameter("title");
	String fileName = req.getFilesystemName("file");//req.getParameter("file");
	String content = req.getParameter("content");

	Notice n = new Notice();
	n.setTitle(title);
	n.setContent(content);
	n.setWriter("jongjin826");

	NoticeDao dao = new JdbcNoticeDao();
	dao.insert(n);

	String noticeCode = dao.lastCode();

	if (req.getFile("file") != null) {
		NoticeFile nf = new NoticeFile();
		nf.setDiscription("");
		nf.setSrc(fileName);

		NoticeFileDao fileDao = new JdbcNoticeFileDao();
		fileDao.insert(nf);

	}
	response.sendRedirect("notice.jsp");
%>