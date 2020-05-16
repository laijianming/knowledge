package server.socket;

import jmlog.JMLog;
import org.junit.Test;
import server.servlet.request.ServletRequest;

import java.io.*;
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
    private int port = 8080;

    SocketThreadPool socketThreadPool = new SocketThreadPool();


    /**
     * jianming
     */
    @Test
    public void test() throws IOException {
        connect();
    }

    public void start() {
        // 启动socket 监听请求
        new Thread(() -> connect()).start();
        JMLog.info(HttpServerSocket.class, "JM Tomcat server started on port(s): " + port + " (http)");
    }


    /**
     * 建立连接
     *  连接建立后的步骤：
     *  1、实例化request对象，并将其参数填充
     *  2、对session对象进行判断操作（判断、生成）
     *
     * @throws IOException
     */
    private void connect() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
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
     * 浏览器发送请求步骤
     *  1、通过浏览器，访问服务器，发送一个浏览器的请求头信息
     *  2、根据http协议规范解析请求头信息
     *  3、根据获取到的资源地址
     *  4、根据资源是否动态做出相应处理（静态或动态）
     *  5、将资源通过通道返回给浏览器，浏览器渲染视图
     */

    /**
     * 服务器接收请求步骤
     *  1、提供服务
     *  2、创建request、response
     *  3、实现一个servlet （使用spi机制来注册）
     *
     */


    /**
     * 连接处理
     * @param socket
     */
    public void handler(Socket socket) {
        BufferedReader bufferedReader = null;
        OutputStreamWriter osw = null;
        try {
            // 处理请求
            httpMessageHandle(socket);
            // 响应
            osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            response(osw);
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
     * 处理http请求报文，并封装到 JMServletRequest 对象中返回
     * @param socket socket连接
     * @return
     */
    public ServletRequest httpMessageHandle(Socket socket) throws IOException {
        // 获取输入流
        InputStream inputStream = socket.getInputStream();
        // 等待缓冲
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[1024];
        // 开始第一次读取
        inputStream.read(bytes);
        count = inputStream.available();
        String string = new String(bytes);
        // 查看请求方法及封装coyote\request
        String[] split = string.split("\r\n");
        System.out.println(string);

        // 读取剩下的内容
        while (count != 0) {
            inputStream.read(bytes);
            System.out.print(new String(bytes));
            count = inputStream.available();
        }
        return null;
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
