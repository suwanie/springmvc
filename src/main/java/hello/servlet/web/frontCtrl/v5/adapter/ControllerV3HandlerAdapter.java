package hello.servlet.web.frontCtrl.v5.adapter;

import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.v3.ControllerV3;
import hello.servlet.web.frontCtrl.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    // v3로 들어오면 true를 반환, 아니면 false
    return (handler instanceof ControllerV3);
  }

  @Override
  public ModelView handle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler
  ) throws ServletException, IOException {
    ControllerV3 controller = (ControllerV3) handler;

    Map<String, String> paramMap = createParamMap(request);
    ModelView mv = controller.process(paramMap);

    return mv;
    // 어뎁터의 역할은 핸들러를 호출해주고 나온 결과으 반환타입을 모델뷰에 맞게끔 반환해줘야 한다.
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
