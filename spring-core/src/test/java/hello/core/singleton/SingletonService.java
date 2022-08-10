package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    // windows pc test
    public static SingletonService getInstance(){
        return instance;
    }
}
