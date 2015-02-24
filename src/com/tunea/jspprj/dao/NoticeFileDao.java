package com.tunea.jspprj.dao;

import java.util.List;

import com.tunea.jspprj.model.NoticeFile;

public interface NoticeFileDao {
	public List<NoticeFile> getNoticeFiles(String NoticeCode);
	public int insert(NoticeFile file);
}
