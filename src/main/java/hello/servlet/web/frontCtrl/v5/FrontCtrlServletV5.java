package hello.servlet.web.frontCtrl.v5;

import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.MyView;
import hello.servlet.web.frontCtrl.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontCtrl.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontCtrl.v5.adapter.ControllerV4HandlerAdapter;
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
    initHandlerMappingMap();

    initHandlerAdapters();
  }

  private void initHandlerMappingMap() {
    handlerMappingMap.put(
      "/front-controller/v5/v3/members/new-form",
      new MemberFormControllerV3()
    );
    handlerMappingMap.put(
      "/front-controller/v5/v3/members/save",
      new MemberSaveControllerV3()
    );
    handlerMappingMap.put(
      "/front-controller/v5/v3/members",
      new MemberListControllerV3()
    );

    // v4 추가 => 어댑터를 넣어줘야 한다.
    handlerMappingMap.put(
      "/front-controller/v5/v4/members/new-form",
      new MemberFormControllerV3()
    );
    handlerMappingMap.put(
      "/front-controller/v5/v4/members/save",
      new MemberSaveControllerV3()
    );
    handlerMappingMap.put(
      "/front-controller/v5/v4/members",
      new MemberListControllerV3()
    );
  }

  private void initHandlerAdapters() { // 2번, 핸들러 찾기 => loop를 돌려 찾으면 된다.
    handlerAdapters.add(new ControllerV3HandlerAdapter());
    handlerAdapters.add(new ControllerV4HandlerAdapter());
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

    MyHandlerAdapter adapter = getHandlerAdapter(handler);

    ModelView mv = adapter.handle(request, response, handler);

    String viewName = mv.getViewName();
    MyView view = viewResolver(viewName);

    view.render(mv.getModel(), request, response);
  }

  private MyHandlerAdapter getHandlerAdapter(Object handler) {
    for (MyHandlerAdapter adapter : handlerAdapters) {
      if (adapter.supports(handler)) {
        // 지원하는가? => 여기선 handler를 지원하는가?
        return adapter;
      }
    }
    throw new IllegalArgumentException(
      "handler adapter를 찾을 수 없다. => " + handler
    );
  }

  private Object getHandler(HttpServletRequest request) {
    String requestURI = request.getRequestURI();

    return handlerMappingMap.get(requestURI);
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }
}
