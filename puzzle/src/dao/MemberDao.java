package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;
public class MemberDao {
	
	/*
	 * SELECT member_id FROM member
	 * WHERE member_id = ? AND member_pw = ? AND member_lever = 'Y' 
	 */
	
	//로그인
	public Member login(Connection conn, Member member) throws Exception {
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println("MemberDao ID : "+member.getMemberId());
		String sql = "SELECT member_id FROM member WHERE member_id = ? AND member_pw = ? AND member_level = 'Y'";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id"));
			}
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return returnMember;
	}
	//MemberDao 메서드 추가
	public void AddMember(Connection conn, Member member)throws Exception {
	      PreparedStatement stmt = null;
	      String sql = "INSERT INTO member(member_id, member_pw, member_level) VALUES (?, ?, 'Y')";
	      try {
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, member.getMemberId());
	         stmt.setString(2, member.getMemberPw());
	         stmt.executeUpdate();
	         } finally {
	            try {
	               stmt.close();
	            } catch(SQLException e) {
	               e.printStackTrace();
	            }
	         }
	   }
	
}
