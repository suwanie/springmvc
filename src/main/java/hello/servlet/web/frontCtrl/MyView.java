package hello.servlet.web.frontCtrl;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyView {

    private String viewPath;

    public MyView() {
    }

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // rendering => view work, 화면을 만드는 활동
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dis = request.getRequestDispatcher(viewPath);
        dis.forward(request, response);

    }

}
