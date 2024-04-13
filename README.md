# Servlet
리포지토리 설명 : 인프런 김영한님의 강의 '스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술' Servlet 파트 스터디 정리
https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1/

# 서블릿(Servlet)
TCP/IP 소켓 생성, HTTP 요청 메시지 및 메시지 바디 내용을 파싱, HTTP 응답 메시지 생성, TCP/IP에 응답 전달, 소켓 종료 와 같은 웹 애플리케이션 서버에서 처리해야하는 공통 작업들을 대신 수행해주며 덕분에 개발자는 비즈니스 로직 개발 부분에 집중 가능

예시)

    @WebServlet(name = "helloServlet", urlPatterns = "/hello") // urlPatterns(/hello)의 URL이 호출되면 서블릿 코드가 실행
    public class HelloServlet extends HttpServlet {
  
      @Override
      protected void service(
        HttpServletRequest request,    // HTTP 요청 정보를 편리하게 사용할 수 있도록 해주는 HttpServletRequest 객체 사용
        HttpServletResponse response) {// HTTP 응답 정보를 편리하게 입력할 수 있도록 해주는 HttpServletResponse 객체 사용
          // 애플리케이션 로직
      }
    }

# 서블릿 작동 방식
1) 클라이언트에서 WAS에 HTTP 요청
2) 요청이 올 때마다 WAS에서 request, response 객체 생성
3) 서블릿 컨테이너에 있는 서블릿 객체 호출하여 파라미터로 requset, response 객체 전달
4) 서블릿에서 response 리턴
5) WAS에서 response 객체 내용을 HTTP 응답 정보에 담아서 클라이언트에 전달

# 서블릿 컨테이너
서블릿 객체를 생성, 초기화, 호출 ,종료하는 생명주기 관리
- 서블릿 객체는 싱글톤으로 관리됨  
→ request/response 객체는 사용자의 요청 내용이 모두 다르기 때문에 계속 새로 생성되는게 맞지만 HTTP 요청/응답을 편리하게 다루게 해주는 서블릿 객체는 굳이 계속 생성할 필요가 없음  
- 동시 요청을 위한 멀티 쓰레드 처리 지원

# 동시 요청 - 멀티 쓰레드와 서블릿
- 싱글쓰레드 : 먼저 온 요청을 처리하는 쓰레드가 지연되면 후속 요청 또한 모두 지연되는 단점이 존재  
- 멀티쓰레드 : 요청이 올 때마다 쓰레드 생성함으로써 선행 쓰레드가 작업이 지연되더라도 나머지 쓰레드는 영향 없이 자신의 작업 수행 가능  
- 멀티쓰레드의 단점 : 요청이 올 때마다 쓰레드가 계속 많이 생성될수록 응답 속도가 느려짐(Context-Switching 비용 발생)  
→ 쓰레드 풀(특정 수의 쓰레드를 미리 생성해놓고 관리)을 통해 최대 허용 쓰레드 수를 관리함으로써 단점 보완

# MVC 패턴의 이해
하나의 서블릿이나 JSP로 비즈니스 로직, 뷰 렌더링을 모두 처리하게 되면 하나가 너무 많은 역할을 수행하게 되며  
유지보수에도 어려움이 생길 수 있음  
→ 각각의 역할별로 나눠 각자의 업무만 담당하게끔 설계하는 것이 MVC 패턴의 핵심!
