package hello.servlet.web.frontCtrl.v4;

import hello.servlet.web.frontCtrl.MyView;
import hello.servlet.web.frontCtrl.v4.controller.MemberFormCtrlV4;
import hello.servlet.web.frontCtrl.v4.controller.MemberListCtrlV4;
import hello.servlet.web.frontCtrl.v4.controller.MemberSaveCtrlV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontCtrlServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontCtrlServletV4 extends HttpServlet {

  private Map<String, ControllerV4> controllerMap = new HashMap<>();

  public FrontCtrlServletV4() {
    controllerMap.put(
      "/front-controller/v4/members/new-form",
      new MemberFormCtrlV4()
    );
    controllerMap.put(
      "/front-controller/v4/members/save",
      new MemberSaveCtrlV4()
    );
    controllerMap.put("/front-controller/v4/members", new MemberListCtrlV4());
  }

  @Override
  protected void service(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    String requestUrl = request.getRequestURI();

    ControllerV4 controller = controllerMap.get(requestUrl);

    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(request);
    Map<String, Object> model = new HashMap<>();

    String viewName = controller.process(paramMap, model);

    MyView view = viewResolver(viewName);
    view.render(model, request, response);
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
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
