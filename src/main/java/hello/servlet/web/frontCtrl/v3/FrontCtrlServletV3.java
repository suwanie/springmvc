package hello.servlet.web.frontCtrl.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.MyView;
import hello.servlet.web.frontCtrl.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontCtrl.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontCtrlServletV3", urlPatterns = "/front-controller/v2/*")
public class FrontCtrlServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontCtrlServletV3() { // 매핑정보를 담아놓자.
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontCtrlServletv3.service()"); // 이런거 log로 찍는게 좋음

        String requestUrl = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestUrl);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }
        // ------------위에까지는 v2랑 똑같

        // 여기서 paramMap을 넘겨준다. => parameter 다 꺼내야한다.
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        // ------------------파라미터는 이제 됐다.

        ModelView mv = controller.process(paramMap);

    }

}
