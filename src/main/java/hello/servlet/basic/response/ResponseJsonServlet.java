package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * HttpServletResponse : 데이터를 Json 형식으로 변환하여 응답
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Content-Type: application/json
        response.setContentType("application/json");
        //response.setCharacterEncoding("utf-8"); // application/json은 스펙상 utf-8을 사용하도록 정의되어 있기 때문에, 이렇게 utf-8로 인코딩 형식을 지정할 필요가 없음

        HelloData helloData = new HelloData();
        helloData.setUsername("wonho");
        helloData.setAge(30);

        // ObejctMapper를 사용하여 helloData => { "username" : "wonho", "age" : 30 }의 json 형식 데이터로 변환
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
