package hello.servlet.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HttpServletRequest : JSON Body API request 처리 방법(ObjectMapper 사용)
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper(); // jackson 라이브러리 추가해야 사용 가능
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        try {
            HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // messageBody를 HelloData 클래스 객체로 변환
            System.out.println(helloData.getUsername());
            System.out.println(helloData.getAge());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response.getWriter().write("ok");
    }
}
