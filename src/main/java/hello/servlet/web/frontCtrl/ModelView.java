package hello.servlet.web.frontCtrl;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelView {

    private String viewName; // view의 논리적 이름
    private Map<String, Object> model = new HashMap<>(); // put으로 원하는 데이터 넣어놓기

    public ModelView() {
    }

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

}
