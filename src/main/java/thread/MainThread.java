package thread;

public class MainThread extends Thread {

    public MainThread() {
    }

    public void start(ThreadEnum name) {
        switch (name) {
            case UPDATE:
                Thread update = new Update();
                break;
            default:
                break;
        }
    }

}
