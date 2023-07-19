package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * HttpServletRequest : 쿼리 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[All Parameter Select] - Start");

        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println();

        System.out.println("[Single Parameter Select]");
        // 하나의 파라미터에 대해서 단 하나의 값만 있을때 사용해야 함(중복일때 사용하면 첫번째 파라미터 값만 반환)
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[Multiple Parameters Select]");
        String[] usernames = request.getParameterValues("username"); // 동일한 이름의 여러 파라미터들이 있을땐 .getParameterValues() 메서드 사용
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok"); // 응답으로 ok 보냄
    }
}
