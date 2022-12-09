package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 스프링빈 이름을 넣어준다? => url패턴으로 맞춤
@Component("/springmvc/old-controller")
public class OldController implements Controller {

  @Override
  public ModelAndView handleRequest(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception {
    System.out.println("OldContoller.handleRequest()");
    return new ModelAndView("new-form"); // 논리이름,
    // => 뷰리졸버에서 물리이름으로 바꿔야한다.
    // 이상태에서하면 404가 뜨는데 이건 그냥 뷰화면이 없어서 그런것,
  }
}
