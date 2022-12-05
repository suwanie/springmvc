package hello.servlet.web.frontCtrl;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

  private String viewPath;

  public MyView() {}

  public MyView(String viewPath) {
    this.viewPath = viewPath;
  }

  // rendering => view work, 화면을 만드는 활동
  public void render(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    RequestDispatcher dis = request.getRequestDispatcher(viewPath);
    dis.forward(request, response);
  }

  public void render(
    Map<String, Object> model,
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    modelToRequestAttribute(model, request);
    RequestDispatcher dis = request.getRequestDispatcher(viewPath);
    dis.forward(request, response);
  }

  private void modelToRequestAttribute(
    Map<String, Object> model,
    HttpServletRequest request
  ) {
    model.forEach((key, value) -> request.setAttribute(key, value));
  }
}
