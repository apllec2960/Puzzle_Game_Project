package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ReportListService;
import service.ReportService;
import vo.Report;

/**
 * Servlet implementation class SelectReportMyRec
 */
@WebServlet("/SelectReportMyRec")
public class SelectReportMyRec extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SelctReportMyRec.doPost 실행!!!! 내 결과");
		response.setContentType("application/json;charset=utf-8");
		Report report = new Report();
		
		String ID = request.getParameter("ID");
		report.setMemberId(ID);
		ReportListService reportListService = new ReportListService();
		List<Report> list = reportListService.reportMyList(10,report);
		
		Gson gson =new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println("MyRec jsonStr" + jsonStr);
		response.getWriter().write(jsonStr);
		
	}

}
