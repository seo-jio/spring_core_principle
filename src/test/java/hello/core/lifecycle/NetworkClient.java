package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출: " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("Connect: " + url);
    }

    public void call(String message){
        System.out.println("Call: " + url + ", Message: " + message);
    }

    public void disconnect(){
        System.out.println("Disconnect: " + url);
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }
}