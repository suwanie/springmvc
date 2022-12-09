package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @RequestMapping("/new-form")
  public ModelAndView newForm() {
    return new ModelAndView("new-form");
  }

  @RequestMapping
  public ModelAndView save() {
    List<Member> members = memberRepository.findAll();
    ModelAndView mv = new ModelAndView("members");
    mv.addObject("members", members);
    return mv;
  }

  @RequestMapping("/save")
  public ModelAndView members(
    HttpServletRequest request,
    HttpServletResponse response
  ) {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    System.out.println("member => " + member);
    memberRepository.save(member);

    ModelAndView mv = new ModelAndView("save-result");
    mv.addObject("member", member);

    return mv;
  }
}
// 그냥 v1거 3개 다 복사하고 경로 v1->v2로 바꿔주고, process라는 함수 이름이 겹치니 이것들을 바꿔준게 끝
