package hello.servlet.web.frontCtrl.v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontCtrl.MyView;
import hello.servlet.web.frontCtrl.v2.controller.MemberFormCtrlV2;
import hello.servlet.web.frontCtrl.v2.controller.MemberListCtrlV2;
import hello.servlet.web.frontCtrl.v2.controller.MemberSaveCtrlV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontCtrlServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontCtrlServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontCtrlServletV2() { // 매핑정보를 담아놓자.
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormCtrlV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveCtrlV2());
        controllerMap.put("/front-controller/v2/members", new MemberListCtrlV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontCtrlServletV2.service()"); // 이런거 log로 찍는게 좋음

        String requestUrl = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestUrl);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);

    }

}
