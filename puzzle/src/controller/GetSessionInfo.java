package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/GetSessionInfo")
public class GetSessionInfo extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json;charset=utf-8");
			System.out.println("GetSessionInfo.doPost 실행!! 세션");

			HttpSession session = request.getSession();
			//session.setAttribute("", "test@test.com");
			
			String sessionInfo = (String)session.getAttribute("sessionInfo");
			System.out.println("GetSessionInfo "+sessionInfo);
			
			Gson gson = new Gson();
			String json = gson.toJson(sessionInfo);
			System.out.println(json);
			response.getWriter().write(json);
	}
}