package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  // get은 여러번 호출해도 사이드이펙트가 없어서 여러 문제가 발생할 수 있다.
  // 그래서 단순조회는 get, data 변경은 post, 이렇게 목적에 맞게 http method를 설정해줘야한다.
  // @RequestMapping(value = "/new-form", method = RequestMethod.GET) => 옛날 버전
  @GetMapping("/new-form")
  public String newForm() {
    return "new-form";
  }

  // @RequestMapping(method = RequestMethod.GET)
  @PostMapping("/save")
  public String save(Model model) {
    List<Member> members = memberRepository.findAll();
    model.addAttribute("members", members);
    return "members";
  }

  // @RequestMapping(value = "/save", method = RequestMethod.POST)
  @GetMapping
  public String save(
    @RequestParam("username") String username,
    @RequestParam("age") int age,
    Model model
  ) {
    Member member = new Member(username, age);
    memberRepository.save(member);

    model.addAttribute("member", member);

    return "save-result";
  }
}
// v2 -> v3로
// Get,Post Mapping으로 바꿨더니 안됨 ㅋ