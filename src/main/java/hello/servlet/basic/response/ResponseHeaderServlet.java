package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * HttpServletResponse 클래스 기본 기능 사용법
 */
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-Line]
        // response.setStatus(200); 이렇게 직접 response 응답코드 설정 가능
        response.setStatus(HttpServletResponse.SC_OK); // 주로 쓰는 방법은 HttpServletResponse 클래스에 정의된 OK 상수값 사용

        // [response-headers]
        // Content-Type/인코딩 형식 지정
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain"); // 이렇게 각각 ContentType과
        response.setCharacterEncoding("utf-8"); // 인코딩 형식 따로 지정 가능

        response.setHeader("Cache-Control", "no-cache, no-store, nust-revalidate"); // 캐시에 대한 설정도 가능(캐시 무효화)
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello"); // 사용자가 임의로 지정한 header 세팅

        //response.setContentLength(3); // (생략시 자동 생성되며, 주로 생략하여 자동으로 ContentLength 생성하도록 하는게 편함)

        // [편의 메서드]
        // 1. 쿠키
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie); // 쿠키 지정

        // 2. Redirect
        // Status Code 302
        // Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location", "/basic/hello-form.html");

        // response.sendRedirect("/basic/hello-form.html"); // 위에 setStatus, setHeader에서 리다이렉트 지정하는 것을 sendRedirect를 통해 한줄로 지정 가능

        // [message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }
}
