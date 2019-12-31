package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import vo.Member;
import vo.Report;

public class ReportDao {
	//기록 추가
	public void AddReport(Connection conn, Report report) throws Exception{
			PreparedStatement stmt = null;
			String sql = "INSERT INTO puzzle_report(member_id, report_date, count, timer) VALUES (?, now(), ?, ?)";
			try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, report.getMemberId());
			stmt.setInt(2, report.getCount());
			stmt.setInt(3, report.getTimer());
			stmt.executeUpdate();
			} finally {
				try {
					stmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
	}
	//탑 10을 출력하는 메소드
	 public List<Report> ReportListLimit(Connection conn, int limit) throws Exception{
	      List<Report> list = new ArrayList<Report>();
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      String sql = "SELECT member_id, report_date, count, timer FROM puzzle_report ORDER BY count DESC, timer DESC LIMIT 0, ?";
	      try {
	      stmt = conn.prepareStatement(sql);
	      stmt.setInt(1, limit);
	      rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	         Report report = new Report();
	         report.setMemberId(rs.getString("member_id"));
	         report.setCount(rs.getInt("count"));
	         report.setTimer(rs.getInt("timer"));
	         report.setReportDate(rs.getString("report_date"));
	         list.add(report);
	      }
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	         } catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	 }
	 
	 //오늘의 탑텐 리스트
	 public List<Report> reportTodatTopList(Connection conn, int limit) throws Exception{
		 List<Report> list =new ArrayList<Report>();
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 String sql = "SELECT * FROM puzzle_report WHERE DATE(report_date) = DATE(NOW()) AND YEAR(report_date) = YEAR(NOW()) AND MONTH(report_date) = MONTH(NOW()) ORDER BY timer DESC LIMIT 0,?;";
		 try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1,limit);
			 rs = stmt.executeQuery();
		 while(rs.next()) {
		     Report report = new Report();
	         report.setMemberId(rs.getString("member_id"));
	         report.setCount(rs.getInt("count"));
	         report.setTimer(rs.getInt("timer"));
	         report.setReportDate(rs.getString("report_date"));
	         list.add(report);
		 }
		}finally {
			try {
				rs.close();
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	 }
	 //이번달 탑텐 리스트
	 public List<Report> reportMonthTopList(Connection conn, int limit) throws Exception{
		 List<Report> list = new ArrayList<Report>();
		 PreparedStatement stmt = null;
		 String sql = "SELECT * FROM puzzle_report WHERE MONTH(report_date) =MONTH(NOW()) AND YEAR(report_date) =YEAR(NOW()) ORDER BY timer LIMIT 0,?";
		 ResultSet rs =null;
		 try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, limit);
			 rs = stmt.executeQuery();
		while(rs.next()) {
			Report report = new Report();
			report.setMemberId(rs.getString("member_id"));
			report.setCount(rs.getInt("count"));
			 report.setTimer(rs.getInt("timer"));
	         report.setReportDate(rs.getString("report_date"));
	         list.add(report);
			 }
		 }finally {
			 try {
				 rs.close();
				 stmt.close();
			 }catch(Exception e) {
				 e.printStackTrace();
				// TODO: handle exception
			}
		 }
		 return list;
	 }
	 //나의 기록 리스트
	 public List<Report> reportMyList(Connection conn, int limit, Report report)throws Exception{
		 List<Report> list = new ArrayList<Report>();
		 PreparedStatement stmt = null;
		 String sql ="SELECT member_id,count,timer,report_date FROM puzzle_report WHERE member_id = ? ORDER BY timer DESC LIMIT 0,? ";
		 ResultSet rs = null;
		 try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setString(1,report.getMemberId());
			 stmt.setInt(2, limit);
			 rs = stmt.executeQuery();
			 while(rs.next()) {
				 Report report2 = new Report();
				 report2.setMemberId(rs.getString("member_id"));
				 report2.setCount(rs.getInt("count"));
				 report2.setTimer(rs.getInt("timer"));
		         report2.setReportDate(rs.getString("report_date"));
		         list.add(report2);
			 }
			 System.out.println("myreport dao : " + list);
		 }finally {
			try {
				rs.close();
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		 }
		 return list;
	 }
}
