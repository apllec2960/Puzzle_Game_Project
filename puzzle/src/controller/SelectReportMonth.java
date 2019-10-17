package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.google.gson.Gson;

import dao.ReportDao;
import service.ReportListService;
import vo.Report;

/**
 * Servlet implementation class SelectReportMonth
 */
@WebServlet("/SelectReportMonth")
public class SelectReportMonth extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SelectReportMonth.doPost 실행 !! 이번달 탑10");
		response.setContentType("application/json;charset=utf-8");
		ReportListService reportListService = new ReportListService();
		List<Report> list =reportListService.reportMonthTopList(10);
		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
	    response.getWriter().write(jsonList);   
	}

}
