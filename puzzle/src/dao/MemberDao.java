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
	//마이페이지 -입력한 아이디를 가져옴.
	public Member SelectMemberInfo(Connection conn, Member member) {
	      Member mem = new Member();
	      // 비밀번호는 함부로 추출하지 않음.
	      String sql = "SELECT member_id FROM member WHERE member_id =?";
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, member.getMemberId());
	         rs = stmt.executeQuery();
	         rs.next();
	         mem.setMemberId(rs.getString("member_Id"));
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            stmt.close();
	            conn.close();
	            rs.close();
	         }catch (Exception e) {
	            e.printStackTrace();
	         }
	      }      
	      return mem;
	   }
	
	//회원탈퇴 메소드
	public void SubMember(Connection conn, Member member)throws Exception {
	      PreparedStatement stmt = null;
	      String sql = "UPDATE member SET member_level = 'N' WHERE member_id = ? AND member_pw = ?;";
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
	
	// 비밀번호 수정 메소드
	public String updateMemberInfo(Connection conn, Member member, String newPw) {
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      String pw = null;
	      try {
	         sql = "SELECT member_pw FROM member WHERE member_id =?";
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, member.getMemberId());
	         rs = stmt.executeQuery();
	         rs.next();
	         pw = rs.getString("member_pw");
	         System.out.println("pw: "+pw + ", 입력받은pw: "+ member.getMemberPw());   
	         
	         // 기존 비밀번호가 틀렸을 때 
	         if(!pw.equals(member.getMemberPw())) {            
	            return "비밀번호가 틀렸습니다.";
	         }      
	         if(pw.equals(newPw)) {            
	            return "수정을 요청하신 비밀번호가 기존 비밀번호와 동일 합니다.";
	         }   
	         
	         sql = "UPDATE member SET member_pw=? WHERE member_id =?";   
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, newPw);
	         stmt.setString(2, member.getMemberId());
	         stmt.executeUpdate();
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            stmt.close();
	            conn.close();
	            rs.close();
	         }catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	      return "정보 수정 완료";
	   }
}
