package server.servlet;

import server.servlet.coyote.Request;

/**
 * 处理请求报文，封装信息到coyote\request中
 * @author jianming
 * @create 2020-05-16-18:59
 */
public class Http11Processor {

    /**
     * 解析第一次读取到的http报文内容，并将相关信息封装到coyote\request中
     * @param content 第一次读取http报文的内容
     * @return
     */
    public Request parseRequestLine(String content) {
        String[] httpContents = content.split("\r\n");
        // 第一行为：请求方法 请求uri http版本
        String[] contentLine1 = httpContents[0].split(" ");
        Request request = new Request();
        return null;
    }


}
