package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ReportDao;
import vo.Member;
import vo.Report;

public class ReportListService {
   
	//탑텐 리스트 보여주는 서비스
   public List<Report> reportTopTenList(int limit){
      List<Report> list = new ArrayList<Report>();
      DBService dbService = new DBService();
      ReportDao reportDao = new ReportDao();
      Connection conn = null;
      
      try {
         conn = dbService.getConnection();
         list = reportDao.ReportListLimit(conn, limit);
      }   catch (Exception e){
            try {conn.rollback();}
            catch(SQLException e1) {}
            e.printStackTrace();
      }finally {
         DBService.close(null, null, conn);
      }
      return list;
   }
   	//오늘의 탑틴 리스트를 보여주는 메소드
   public List<Report> reportTodayTopList(int limit){
	   List<Report> list = new ArrayList<Report>();
	      DBService dbService = new DBService();
	      ReportDao reportDao = new ReportDao();
	      Connection conn = null;
	      
	      try {
	         conn = dbService.getConnection();
	         list = reportDao.reportTodatTopList(conn, limit);
	      }   catch (Exception e){
	            try {conn.rollback();}
	            catch(SQLException e1) {}
	            e.printStackTrace();
	      }finally {
	         DBService.close(null, null, conn);
	      }
	      return list;
   }
   
   //이번달 탑텐 리스트
   public List<Report> reportMonthTopList(int limit){
	   List<Report> list = new ArrayList<Report>();
	   DBService dbService = new DBService();
	   ReportDao reportDao = new ReportDao();
	   Connection conn = null;
	   try {
	         conn = dbService.getConnection();
	         list = reportDao.reportMonthTopList(conn, limit);
	      }   catch (Exception e){
	            try {conn.rollback();}
	            catch(SQLException e1) {}
	            e.printStackTrace();
	      }finally {
	         DBService.close(null, null, conn);
	      }
	      return list;
   }
 //내꺼 리스트
   public List<Report> reportMyList(int limit, Report report){
	   List<Report> list = new ArrayList<Report>();
	   DBService dbService = new DBService();
	   ReportDao reportDao = new ReportDao();
	   Connection conn = null;
	   try {
	         conn = dbService.getConnection();
	         list = reportDao.reportMyList(conn, limit, report);
	         System.out.println("reportMyList list : " + list);
	      }   catch (Exception e){
	            try {conn.rollback();}
	            catch(SQLException e1) {}
	            e.printStackTrace();
	      }finally {
	         DBService.close(null, null, conn);
	      }
	      return list;
   }
}