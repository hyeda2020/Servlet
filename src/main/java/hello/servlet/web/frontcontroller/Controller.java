package hello.servlet.web.frontcontroller;

import java.util.Map;

public interface Controller {

    /**
     * @param paramMap
     * @return viewName
     */
    ModelView process(Map<String, String> paramMap);
}
