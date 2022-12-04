package hello.servlet.web.frontCtrl.v2.controller;

import java.io.IOException;

import hello.servlet.web.frontCtrl.MyView;
import hello.servlet.web.frontCtrl.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberFormCtrlV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 변수에 담지 않고 바로 리턴
        return new MyView("/WEB-INF/views/new-form.jsp");

    }

}
