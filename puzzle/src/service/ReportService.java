package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ReportDao;
import vo.Report;

public class ReportService {
	
	//기록추가 메소드
	public void AddReport(Report report) {
		Connection conn = null;
		
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			ReportDao reportDao = new ReportDao();
			reportDao.AddReport(conn, report);
			
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
