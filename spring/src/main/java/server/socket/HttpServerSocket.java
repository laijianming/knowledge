package server.socket;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收来自客户端的请求并做相关的处理
 * 响应客户端的请求
 * @author jianming
 * @create 2020-05-15-15:38
 */
public class HttpServerSocket {

    /**
     * TODO @Value 用@Value来读取配置文件中的属性
     */
    private int port;

    SocketThreadPool socketThreadPool = new SocketThreadPool();


    /**
     * jianming
     */
    @Test
    public void test() throws IOException {
        connect();
    }

    /**
     * 建立连接
     *  连接建立后的步骤：
     *  1、实例化request对象，并将其参数填充
     *  2、对session对象进行判断操作（判断、生成）
     *
     * @throws IOException
     */
    public void connect() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket socket = serverSocket.accept();
                if(socket != null) {
                    // 交给线程池去处理请求
                    socketThreadPool.execute(() -> handler(socket));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }




    /**
     * 连接处理
     * @param socket
     */
    public void handler(Socket socket) {
        BufferedReader bufferedReader = null;
        OutputStreamWriter osw = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.equals("")) {
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("客户端接受异常" + e.getMessage());
        }finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 响应处理
     * @param osw
     * @throws Exception
     */
    public void response(OutputStreamWriter osw) throws Exception {
        osw.write("HTTP/1.1 200 OK\r\n");
        osw.write("Server: Apache-Coyote/1.1\r\n");
        osw.write("Set-Cookie: JSESSIONID=03493794995CE31A0F131787B6C6CBB2; Path=/; HttpOnly\r\n");
        osw.write("Content-Type: text/html;charset=UTF-8\r\n");
        osw.write("Transfer-Encoding: chunked\r\n");
        osw.write("Date: Tue, 19 May 2015 02:48:27 GMT\r\n");
        osw.write("\r\n");
        osw.write("c9\r\n");
        osw.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
        osw.write("<HTML>\r\n");
        osw.write("  <HEAD><TITLE>A Servlet</TITLE></HEAD>\r\n");
        osw.write("  <BODY>\r\n");
        osw.write("    This is class com.serv.myServ, using the GET method\r\n");
        osw.write("  </BODY>\r\n");
        osw.write("</HTML>\r\n");
        osw.write("\r\n");
        osw.write("0");
        osw.write("\r\n");
        osw.write("\r\n");
        osw.flush();
    }


}
