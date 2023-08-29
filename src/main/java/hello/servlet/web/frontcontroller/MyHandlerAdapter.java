package hello.servlet.web.frontcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    /**
     * @param handler
     * @return 어댑터가 해당 컨트롤러를 처리할 수 있는지 여부
     */
    boolean supports(Object handler);

    /**
     * @param request
     * @param response
     * @param handler
     * @return 어댑터가 실제 컨트롤러를 호출하고 그 결과로 ModelView 리턴
     * @throws ServletException
     * @throws IOException
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
