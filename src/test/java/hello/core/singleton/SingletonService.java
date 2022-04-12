package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //생성자를 private으로 막아둔다.
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 호출 로직");
    }
}
