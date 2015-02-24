package com.tunea.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tunea.jspprj.dao.NoticeDao;
import com.tunea.jspprj.model.Notice;

public class JdbcNoticeDao implements NoticeDao {

	@Override
	public Notice getNotice(String code) {

		/*
		 * String url =
		 * "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		 */
		String sql = "select * from notices where code='" + code + "'";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			// 모델 마련하기
			Notice n = new Notice();

			rs.next();

			n.setCode(rs.getString("code"));
			n.setTitle(rs.getString("title"));
			n.setWriter(rs.getString("writer"));
			n.setRegdate(rs.getDate("regdate"));
			n.setHit(rs.getInt("hit"));
			n.setContent(rs.getString("content"));

			rs.close();
			st.close();
			con.close();
			return n;

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
	public List<Notice> getNotices(int page, String query, String field /*
																		 * =
																		 * WRITER
																		 * or
																		 * TITLE
																		 * or
																		 * ETC
																		 */) {
		// TODO Auto-generated method stub
		// SELECT * FROM (SELECT ROWNUM NUM, NOTICES.* FROM (SELECT * FROM
		// NOTICES ORDER BY REGDATE DESC) NOTICES) WHERE NUM BETWEEN 11 AND 20;

		int start = 20 * (page - 1) + 1;
		int end = 20 * (page - 1) + 20;

		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		/*
		 * String sql = "SELECT * FROM " + "(SELECT ROWNUM NUM, N.* FROM " +
		 * "(SELECT * FROM NOTICES WHERE " + field +
		 * " like ? ORDER BY REGDATE DESC) N) " + "WHERE NUM BETWEEN ? AND ?";
		 */

		String sql = "select * from (select (row_number() over (order by REGDATE desc))"
				+ " num, notices.* from notices WHERE "
				+ field
				+ " like ?) n where n.num between ? and ?";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			// 모델 마련하기

			st.setString(1, "%" + query + "%");
			st.setInt(2, start);
			st.setInt(3, end);

			ResultSet rs = st.executeQuery();

			List<Notice> list = new ArrayList<Notice>();

			while (rs.next()) {
				Notice n = new Notice();

				n.setCode(rs.getString("code"));
				n.setTitle(rs.getString("title"));
				n.setWriter(rs.getString("writer"));
				n.setRegdate(rs.getDate("regdate"));
				n.setHit(rs.getInt("hit"));
				n.setContent(rs.getString("content"));
				list.add(n);
			}
			rs.close();
			st.close();
			con.close();

			return list;

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
	public List<Notice> getNotices(int page, String query) {
		// TODO Auto-generated method stub
		return getNotices(page, query, "TITLE");
	}

	@Override
	public List<Notice> getNotices(int page) {
		// TODO Auto-generated method stub
		return getNotices(page, "");
	}

	@Override
	public int insert(Notice notice) {
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		String sql = "insert into notices(code, title, writer, content, regdate, hit) values(?,?,?,?,GETDATE(),0)";
		String sqlCode = "SELECT isnull(MAX(CAST(CODE as int)),0)+1 CODE FROM NOTICES";

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
			pst.setString(2, notice.getTitle());
			pst.setString(3, notice.getWriter());
			pst.setString(4, notice.getContent());

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

	/*
	 * public int createCode(){ String url =
	 * "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb"; String
	 * sql = "select count(*) from notice where code=?";
	 * 
	 * try { Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 * Connection con = DriverManager.getConnection(url, "sist", "newlec");
	 * PreparedStatement pst = con.prepareStatement(sql); int randomCode = 0;
	 * while(true){ randomCode = new Random().nextInt(1000); pst.setString(1,
	 * String.valueOf(randomCode));
	 * 
	 * ResultSet rs = pst.executeQuery();
	 * 
	 * if(rs.getInt("count(*)") == 0) break; } pst.close(); con.close(); return
	 * randomCode;
	 * 
	 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * return 0; }
	 */

	@Override
	public int update(Notice notice) {
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		String sql = "update notices set title=?, content=?  where code=?";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);

			// 모델 마련하기

			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getCode());

			int result = st.executeUpdate();
			st.close();
			con.close();
			return result;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int delete(String code) {
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		String sql = "delete from notices where code=?";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, code);

			int result = st.executeUpdate();
			st.close();
			con.close();
			return result;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int getSize(String query, String field) {

		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		String sql = "SELECT count(*) count FROM NOTICES WHERE " + field
				+ " like ?";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			// 모델 마련하기

			st.setString(1, "%" + query + "%");

			ResultSet rs = st.executeQuery();

			rs.next();
			int count = rs.getInt("count");

			rs.close();
			st.close();
			con.close();

			return count;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int getSize(String query) {

		return getSize(query, "TITLE");
	}

	@Override
	public String lastCode() {
		String sql = "SELECT isnull(MAX(cast(CODE as int)),0) CODE FROM NOTICES";

		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			Statement st = con.createStatement();
			// 모델 마련하기

			ResultSet rs = st.executeQuery(sql);

			rs.next();
			String code = rs.getString("CODE");

			rs.close();
			st.close();
			con.close();

			return code;
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
	public Notice prevNotice(String curcode) {
		// SELECT TOP 1 * FROM NOTICES WHERE REGDATE > (SELECT REGDATE FROM
		// NOTICES WHERE CODE = '242') ORDER BY REGDATE ASC;

		String sql = "SELECT TOP 1 * FROM NOTICES WHERE REGDATE > (SELECT REGDATE FROM NOTICES WHERE CODE = ?) ORDER BY REGDATE ASC";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, curcode);

			ResultSet rs = st.executeQuery();
			// 모델 마련하기
			Notice n = new Notice();

			rs.next();

			n.setCode(rs.getString("code"));
			n.setTitle(rs.getString("title"));
			n.setWriter(rs.getString("writer"));
			n.setRegdate(rs.getDate("regdate"));
			n.setHit(rs.getInt("hit"));
			n.setContent(rs.getString("content"));

			rs.close();
			st.close();
			con.close();
			return n;

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
	public Notice nextNotice(String curcode) {
		// SELECT TOP 1 * FROM NOTICES WHERE REGDATE < (SELECT REGDATE FROM
		// NOTICES WHERE CODE = '242') ORDER BY REGDATE DESC;

		String sql = "SELECT TOP 1 * FROM NOTICES WHERE REGDATE < (SELECT REGDATE FROM NOTICES WHERE CODE = ?) ORDER BY REGDATE DESC";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, curcode);

			ResultSet rs = st.executeQuery();
			// 모델 마련하기
			Notice n = new Notice();

			rs.next();

			n.setCode(rs.getString("code"));
			n.setTitle(rs.getString("title"));
			n.setWriter(rs.getString("writer"));
			n.setRegdate(rs.getDate("regdate"));
			n.setHit(rs.getInt("hit"));
			n.setContent(rs.getString("content"));

			rs.close();
			st.close();
			con.close();
			return n;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
