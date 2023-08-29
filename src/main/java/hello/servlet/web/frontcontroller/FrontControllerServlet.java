package hello.servlet.web.frontcontroller;

import hello.servlet.web.frontcontroller.adapter.ControllerHandlerAdapter;
import hello.servlet.web.frontcontroller.controller.MemberFormController;
import hello.servlet.web.frontcontroller.controller.MemberListController;
import hello.servlet.web.frontcontroller.controller.MemberSaveController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServlet", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServlet() { // 기존 Controller 들을 모두 미리 등록

        initHandlerMappingMap();
        initHandlerAdapters();
    }

    /**
     * 핸들러 매핑정보들을 미리 등록
     */
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v4/members/new-form", new MemberFormController());
        handlerMappingMap.put("/front-controller/v4/members", new MemberListController());
        handlerMappingMap.put("/front-controller/v4/members/save", new MemberSaveController());
    }

    /**
     * 해당 컨트롤러를 다룰 수 있는 핸들러 어댑터를 등록해놓음
     */
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerHandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request); // 핸들러를 처리할 수 있는 어댑터 조회

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        /**
         * 컨트롤러에 직접 접근해서 비즈니스 로직을 실행하는 대신, 해당 컨트롤러를 다룰 수 있는 HandlerAdapter를 통해
         * HandlerAdapter -> Controller(Handler)의 방식으로 간접적으로 비즈니스 로직을 실행하고,
         * 그 결과를 ModelView로 받아옴
         */
        MyHandlerAdapter myHandlerAdapter = getHandlerAdapter(handler);
        ModelView mv = myHandlerAdapter.handle(request, response, handler);

        MyView myView = viewResolver(mv.getViewName());  // 컨트롤러로부터 결과를 담은 model과, 이동할 물리적 View 경로를 받아옴
        myView.render(mv.getModel(), request, response);
    }

    /**
     * @param request
     * @return 핸들러 매핑정보에서 요청URL에 맞는 컨트롤러 객체 리턴
     */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    /**
     * @param handler
     * @return 해당 Controller를 처리할 수 있는지 체크하고 확인되면 그에 맞는 핸들러 객체 리턴
     * @throws IllegalArgumentException
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("해당 handler adapter를 찾을 수 없습니다.");
    }

    /***
     * @param viewName
     * @return 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
