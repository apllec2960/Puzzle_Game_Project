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
import vo.Report;


@WebServlet("/SelectReportToday")
public class SelectReportToday extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("application/json");
      System.out.println("SelectReportToday.doPost 실행!! 오늘탑10");
      ReportListService reportListService = new ReportListService();
      
      List<Report> list = reportListService.reportTodayTopList(10);
            
      Gson gson = new Gson();
      String jsonList = gson.toJson(list);
      System.out.println(jsonList);
      response.getWriter().write(jsonList);   
   }
}