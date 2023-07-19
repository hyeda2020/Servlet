package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

/**
 * Json 형식 데이터를 받아와서 HelloData 클래스 객체 멤버변수에 대입
 */
@Getter
@Setter
public class HelloData {

    private String username;
    private int age;
}
