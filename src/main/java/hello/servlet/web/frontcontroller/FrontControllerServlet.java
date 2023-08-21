package hello.servlet.web.frontcontroller;

import hello.servlet.web.frontcontroller.controller.MemberFormController;
import hello.servlet.web.frontcontroller.controller.MemberListController;
import hello.servlet.web.frontcontroller.controller.MemberSaveController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();

    public FrontControllerServlet() { // 기존 Controller 들을 모두 미리 등록
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormController());
        controllerMap.put("/front-controller/v4/members", new MemberListController());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestUrl = request.getRequestURI();

        Controller controller = controllerMap.get(requestUrl); // 요청 URI에 맞는 컨트롤러 객체를 가져옴
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParam(request);
        Map<String, Object> model = new HashMap<>();

        /**
         * 요청 파라미터를 컨트롤러에 넘겨서 비즈니스 로직을 실행하고,
         * 그 결과를 담은 model과, 이동할 View 경로를 받아옴
         */
        String viewName = controller.process(paramMap, model);

        MyView myView = viewResolver(viewName); //
        myView.render(model, request, response);
    }

    /***
     * @param request
     * @return request 객체로부터 요청 파라미터들을 추출 후 Map으로 변환
     */
    private Map<String, String> createParam(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(
                        paramName -> paramMap.put(paramName, request.getParameter(paramName))
        );

        return paramMap;
    }

    /***
     * @param viewName
     * @return 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
