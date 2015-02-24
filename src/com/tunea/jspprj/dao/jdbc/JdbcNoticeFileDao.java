package com.tunea.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tunea.jspprj.dao.NoticeFileDao;
import com.tunea.jspprj.model.NoticeFile;

public class JdbcNoticeFileDao implements NoticeFileDao {

	@Override
	public List<NoticeFile> getNoticeFiles(String NoticeCode) {
		/*
		 * String url =
		 * "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		 */
		String sql = "select * from noticefiles where code=?";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, NoticeCode);
			ResultSet rs = st.executeQuery();
			// 모델 마련하기

			List<NoticeFile> noticelist = new ArrayList<NoticeFile>();
			while (rs.next()) {

				NoticeFile n = new NoticeFile();

				n.setCode(rs.getString("code"));
				n.setSrc(rs.getString("src"));
				n.setRegdate(rs.getDate("regdate"));
				n.setDiscription(rs.getString("discription"));
				n.setNoticeCode(rs.getString("noticecode"));
				noticelist.add(n);
			}

			rs.close();
			st.close();
			con.close();
			return noticelist;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insert(NoticeFile file) {
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		String sql = "insert into noticefiles values(?,?,getdate(),?,?)";

		String sqlCode = "SELECT isnull(MAX(cast(CODE as int)),0)+1 CODE FROM NOTICEFILES";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement pst = con.prepareStatement(sql);

			// 모델 마련하기

			/*
			 * //format1! Statement st = con.createStatement(); String
			 * getcountSql = "select count(*) from notices"; ResultSet rs =
			 * st.executeQuery(getcountSql); rs.next(); pst.setString(1,
			 * String.valueOf(rs.getInt("count(*)") + 1)); rs.close();
			 * st.close();
			 * 
			 * //format2! code = String.valueOf(createCode()); pst.setString(1,
			 * code);
			 */

			// format3!
			Statement stCode = con.createStatement();
			ResultSet rsCode = stCode.executeQuery(sqlCode);
			rsCode.next();
			String code = rsCode.getString("CODE");
			rsCode.close();
			stCode.close();

			pst.setString(1, code);
			pst.setString(2, file.getSrc());
			pst.setString(3, file.getDiscription());
			pst.setString(4, file.getNoticeCode());

			int result = pst.executeUpdate();
			pst.close();
			con.close();
			return result;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
