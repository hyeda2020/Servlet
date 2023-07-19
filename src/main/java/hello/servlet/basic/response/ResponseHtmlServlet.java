package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServletResponse : Html 응답
 */
@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Content-Type: text/html;charset=utf-8 지정
        response.setContentType("text/html"); // HTTP 응답으로 HTMl을 반환할 때에는 "text/html" 으로 Content-Type 지정해야 함 
        response.setCharacterEncoding("utf-8");

        // 2. 반환할 html 내용 작성
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("  <div>안녕하세요</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
