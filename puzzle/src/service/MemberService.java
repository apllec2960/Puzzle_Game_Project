package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import vo.Member;

public class MemberService {
	//로그인 service
	public String login(Member member) {
		Member returnMember = null;
		Connection conn = null;
		try	{
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			MemberDao memberDao = new MemberDao();
			//before
			System.out.println("MemberService memberId"+member.getMemberId());
			returnMember = memberDao.login(conn, member);
			//after
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("returnService returnidS"+returnMember.getMemberId());
		return returnMember.getMemberId();
	}
	
	//회원가입 service
	public void AddMember(Member member) {
	      Connection conn = null;
	      MemberDao memberDao = null;
	      try   {
	         DBService dbService = new DBService();
	         conn = dbService.getConnection();
	         memberDao = new MemberDao();
	         memberDao.AddMember(conn, member);
	         conn.commit();
	      } catch(Exception e) {
	         try {
	            conn.rollback();
	         } catch(SQLException e1) {
	            e1.printStackTrace();
	         }
	      } finally {
	         try {
	            conn.close();
	         } catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	//회원 정보를 가져옴.
	public String selectMemberInfo(Member member) {
	      Connection conn = null;
	      Member returnMember = null;
	      try {
	         conn = DBService.getConnection();
	         MemberDao memberDao = new MemberDao();
	         // < before >
	         returnMember = memberDao.SelectMemberInfo(conn, member);
	         // < after >
	      }catch (Exception e) {
	         try {
	               conn.rollback();
	            } catch(SQLException e1) {
	               e1.printStackTrace();
	            }
	      }finally{
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return returnMember.getMemberId();
	   }
	
	//회원탈퇴
	public void SubMember(Member member) {
	      Connection conn = null;
	      MemberDao memberDao = null;
	      try   {
	         conn = DBService.getConnection();
	         memberDao = new MemberDao();
	         memberDao.SubMember(conn, member);
	         conn.commit();
	      } catch(Exception e) {
	         try {
	            conn.rollback();
	         } catch(SQLException e1) {
	            e1.printStackTrace();
	         }
	      } finally {
	         try {
	            conn.close();
	         } catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	//회원정보 수정
	//업데이트 서비스
	public String UpdateMemberInfo(Member member, String newPw) {
	      Connection conn = null;
	      String result = null;
	      try {
	         conn = DBService.getConnection();
	         MemberDao memberDao = new MemberDao();
	         result = memberDao.updateMemberInfo(conn, member, newPw);
	      }catch (Exception e) {
	         try {
	            conn.rollback();
	         } catch(SQLException e1) {
	            e1.printStackTrace();
	         }
	      }finally{
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return result;
	   }
}