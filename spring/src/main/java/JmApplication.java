import server.socket.HttpServerSocket;

/**
 * 启动类
 * @author jianming
 * @create 2020-05-16-11:35
 */
public class JmApplication {


    public static void main(String[] args) {
        new HttpServerSocket().start();
    }


}
