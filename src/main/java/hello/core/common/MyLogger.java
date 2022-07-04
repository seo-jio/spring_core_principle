package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//프록시를 사용하여 스프링 컨테이너에 가짜 MyLogger를 등록해 두고 실제 기능 사용 시 진짜 MyLogger를 생성하여 기능 사용한다.
//진짜 객체 조회를 꼭 필요한 시점까지 지연한다!
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] " + "request scope bean create: " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] " + "request scope bean close: " + this);
    }
}
