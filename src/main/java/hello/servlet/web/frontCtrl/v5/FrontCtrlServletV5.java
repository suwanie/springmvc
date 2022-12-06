package hello.servlet.web.frontCtrl.v5;

import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.MyView;
import hello.servlet.web.frontCtrl.v3.ControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontCtrl.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    initHandlerMappingMap(); // public이면 왜 접근을 못하지?

    initHandlerAdapters();
  }

  private void initHandlerMappingMap() {
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

  private void initHandlerAdapters() {
    handlerAdapters.add(new ControllerV3HandlerAdapter());
  }

  @Override
  protected void service(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    Object handler = getHandler(request);

    if (handler == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(request);
    ModelView mv = controller.process(paramMap);

    String viewName = mv.getViewName();
    MyView view = viewResolver(viewName);

    view.render(mv.getModel(), request, response);
  }

  private MyView viewResolver(String viewName) {
    return null;
  }

  private Map<String, String> createParamMap(HttpServletRequest request) {
    return null;
  }

  private Object getHandler(HttpServletRequest request) {
    String requestURI = request.getRequestURI();

    return handlerMappingMap.get(requestURI);
  }
}
