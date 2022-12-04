package hello.servlet.web.frontCtrl.v3;

import java.util.Map;

import hello.servlet.web.frontCtrl.ModelView;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);

}
