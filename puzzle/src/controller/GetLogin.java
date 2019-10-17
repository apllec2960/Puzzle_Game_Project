package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.MemberService;
import vo.Member;

@WebServlet("/GetLogin")
public class GetLogin extends HttpServlet {
	private MemberService memberService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		memberService = new MemberService();
		Member member = new Member();
		
		String memberId = request.getParameter("memberId");
		System.out.println("memberId : "+ memberId);
		String memberPw = request.getParameter("memberPw");
		System.out.println("memberPw : "+ memberPw);
		
		
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		System.out.println("member.getMemberID :"+member.getMemberId());
		System.out.println("member.getMemberPw :"+member.getMemberPw());
		String returnMemberId = memberService.login(member);
		System.out.println("returnMemberId ID : "+returnMemberId);
		HttpSession session = request.getSession();
		session.setAttribute("sessionInfo", returnMemberId);
		
		Gson gson = new Gson();
		String json = gson.toJson(member);
		response.getWriter().write(json);
		
	}
}