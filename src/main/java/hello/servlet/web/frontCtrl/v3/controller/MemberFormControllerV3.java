package hello.servlet.web.frontCtrl.v3.controller;

import java.util.Map;

import hello.servlet.web.frontCtrl.ModelView;
import hello.servlet.web.frontCtrl.v3.ControllerV3;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); // 논리적 주소 이름만 넣어준다.
    }

}