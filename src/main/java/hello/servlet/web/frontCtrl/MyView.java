package hello.servlet.web.frontCtrl;

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

  public void render( // jsp는 request.getAttribute를 쓰기때문에 model 데이털르 다 꺼내야 한다.
    Map<String, Object> model,
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    modelToRequestAttribute(model, request);
    model.forEach((key, value) -> request.setAttribute(key, value));
    RequestDispatcher dis = request.getRequestDispatcher(viewPath);
    dis.forward(request, response);
  }

  // render가 오면 model의 값을 다 꺼내 아래의 request에 다 넣어서 끝나면 render에서 requset가 되어서 jsp가 그 값ㅇ르 사용한다.
  private void modelToRequestAttribute(
    Map<String, Object> model,
    HttpServletRequest request
  ) {
    model.forEach((key, value) -> request.setAttribute(key, value));
  }
}
