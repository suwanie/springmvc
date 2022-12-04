package hello.servlet.web.servletmvc;

import java.io.IOException;
import java.util.List;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("MvcMemberListServlet.service()");

        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        String path = "/WEB-INF/views/members.jsp";

        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }
}

// request 객체를 사용해서 List<Member> members 를 모델에 보관했다