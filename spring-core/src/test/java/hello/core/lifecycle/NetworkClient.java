package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


/*
    InitializingBean 은 afterPropertiesSet() 메서드로 초기화를 지원한다.
    DisposableBean 은 destroy() 메서드로 소멸을 지원한다.

    초기화, 소멸 인터페이스 단점
    이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
    초기화, 소멸 메서드의 이름을 변경할 수 없다.
    내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
    -> 옛날거라 사용x
    public class NetworkClient implements InitializingBean, DisposableBean {
*/

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }
    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }


    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }

/*
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
    */
}
