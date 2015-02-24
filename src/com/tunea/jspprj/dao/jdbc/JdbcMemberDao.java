package com.tunea.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tunea.jspprj.dao.MemberDao;
import com.tunea.jspprj.model.Member;
public class JdbcMemberDao implements MemberDao {

	@Override
	public Member getMember(String uid) {
		String sql = "select * from members where mid = ?";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uid);
			
			ResultSet rs = st.executeQuery();
			// 모델 마련하기
			Member n = new Member();

			rs.next();

			n.setId(rs.getString("MID"));
			n.setName(rs.getString("Name"));
			n.setPw(rs.getString("Pwd"));

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
