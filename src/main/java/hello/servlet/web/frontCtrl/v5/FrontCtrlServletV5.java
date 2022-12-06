package hello.servlet.web.frontCtrl.v5;

import hello.servlet.web.frontCtrl.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontCtrlServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontCtrlServletV5 extends HttpServlet {

  // private Map<String, ControllerV4> controllerMap = new HashMap<>();
  // 기존의 위에것과 비교하면, Object로 모든 타입을 받는다는 것이다. => 그래야 v1,2,3,4,5 일단 받고 분류
  private final Map<String, Object> handlerMappingMap = new HashMap<>();
  // 여러 핸들러(컨트롤러)중 원하는 것을 꺼내쓰기 위해 List 생성
  private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

  public FrontCtrlServletV5() {
    handlerMappingMap.put(
      "/front-controller/v3/members/new-form",
      new MemberFormControllerV3()
    );
    handlerMappingMap.put(
      "/front-controller/v3/members/save",
      new MemberSaveControllerV3()
    );
    handlerMappingMap.put(
      "/front-controller/v3/members",
      new MemberListControllerV3()
    );
  }
}
