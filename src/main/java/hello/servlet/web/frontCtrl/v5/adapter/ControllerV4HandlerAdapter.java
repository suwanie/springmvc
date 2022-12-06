package hello.servlet.web.frontCtrl.v5.adapter;

import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.v4.ControllerV4;
import hello.servlet.web.frontCtrl.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    // V4 컨트롤러를 지원하는지?
    return (handler instanceof ControllerV4);
  }

  @Override
  public ModelView handle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler
  ) throws ServletException, IOException {
    ControllerV4 controller = (ControllerV4) handler;

    Map<String, String> paramMap = createParamMap(request);
    HashMap<String, Object> model = new HashMap<>();

    String viewName = controller.process(paramMap, model);

    // 이게 없으면 viewName 리턴이 안됨 => 타입이 맞지 않음, 어뎁터역할을 한다.
    ModelView mv = new ModelView(viewName);
    mv.setModel(model);

    return mv;
  }

  private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    request
      .getParameterNames()
      .asIterator()
      .forEachRemaining(paramName ->
        paramMap.put(paramName, request.getParameter(paramName))
      );

    return paramMap;
  }
}
