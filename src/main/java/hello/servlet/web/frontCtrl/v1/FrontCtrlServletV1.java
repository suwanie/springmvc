package hello.servlet.web.frontCtrl.v1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontCtrl.v1.controller.MemberFormCtrlV1;
import hello.servlet.web.frontCtrl.v1.controller.MemberListCtrlV1;
import hello.servlet.web.frontCtrl.v1.controller.MemberSaveCtrlV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontCtrlServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontCtrlServletV1 extends HttpServlet {

    // mapping info => 어떤 url로 들어와서 v1을 꺼내서 호출해 ?!
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontCtrlServletV1() { // 매핑정보를 담아놓자.
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormCtrlV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveCtrlV1());
        controllerMap.put("/front-controller/v1/members", new MemberListCtrlV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontCtrlServletV1.service()"); // 이런거 log로 찍는게 좋음

        // Returns the part of this request's URL from the protocol name up to the query
        // string in the first line of the HTTP request.
        // 이러면 url부분을 받을 수 있다.
        // http://localhost:8055/front-controller/v1/members/new-form
        // 위에서 로컬호스트를 뺀 => 그러면 매핑정보의 url과 똑같다.
        String requestUrl = request.getRequestURI();

        // 해당 url에 맞는 객체 인스턴스가 반환이 되어 이 변수에 담긴다.
        ControllerV1 controller = controllerMap.get(requestUrl);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        controller.process(request, response);

    }

}
