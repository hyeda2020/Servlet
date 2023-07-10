package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // 서블릿 애노테이션, name : 서블릿 이름, urlPatterns : URL매핑
public class HelloServlet extends HttpServlet {

    @Override
    // HTTP 요청을 통해 매핑된 URL이 호출되면 서블릿 컨테이너는 아래 service 메서드 실행
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // /hello?username=kim 쿼리파라미터
        System.out.println(username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
