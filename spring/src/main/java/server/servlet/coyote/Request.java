package server.servlet.coyote;

import server.bean.MessageBytes;

import javax.xml.soap.MimeHeaders;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里封装请求的信息
 * 这个类不是用于用户代码的——tomcat在内部使用它以最有效的方式处理请求。
 * 用户（servlet）可以使用facade访问信息，facade提供请求的高级视图。
 * @author jianming
 * @create 2020-05-16-16:46
 */
public final class Request {

    // ----------------------------------------------------------- Constructors

    public Request() {
    }


    // ----------------------------------------------------- Instance Variables

    private int serverPort = -1;
    private final MessageBytes serverNameMB = MessageBytes.newInstance();

    private int remotePort;
    private int localPort;

    private final MessageBytes schemeMB = MessageBytes.newInstance();

    private final MessageBytes methodMB = MessageBytes.newInstance();
    private final MessageBytes uriMB = MessageBytes.newInstance();
    private final MessageBytes decodedUriMB = MessageBytes.newInstance();
    private final MessageBytes queryMB = MessageBytes.newInstance();
    private final MessageBytes protoMB = MessageBytes.newInstance();

    // remote address/host
    private final MessageBytes remoteAddrMB = MessageBytes.newInstance();
    private final MessageBytes localNameMB = MessageBytes.newInstance();
    private final MessageBytes remoteHostMB = MessageBytes.newInstance();
    private final MessageBytes localAddrMB = MessageBytes.newInstance();

    private final MimeHeaders headers = new MimeHeaders();
    private final Map<String,String> trailerFields = new HashMap<>();

    /**
     * Path parameters 路径上的参数
     */
    private final Map<String,String> pathParameters = new HashMap<>();

    /**
     * url 解码器
     * URL decoder.
     */
//    private final UDecoder urlDecoder = new UDecoder();

    private long contentLength = -1L;

    private MessageBytes contentTypeMB = null;

    /**
     * 请求时间，用于避免重复请求
     * Time of the request - useful to avoid repeated calls to System.currentTime
     */
    private long startTime = -1;

//    public UDecoder getURLDecoder() {
//        return urlDecoder;
//    }

    // -------------------- Request data --------------------

    /**
     * 脚本 如：http
     * @return
     */
    public MessageBytes scheme() {
        return schemeMB;
    }

    public MessageBytes method() {
        return methodMB;
    }

    public MessageBytes requestURI() {
        return uriMB;
    }

    public MessageBytes decodedURI() {
        return decodedUriMB;
    }

    public MessageBytes queryString() {
        return queryMB;
    }

    public MessageBytes protocol() {
        return protoMB;
    }

    /**
     * Get the "virtual host", derived from the Host: header associated with
     * this request.
     *
     * @return The buffer holding the server name, if any. Use isNull() to check
     *         if there is no value set.
     */
    public MessageBytes serverName() {
        return serverNameMB;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort ) {
        this.serverPort=serverPort;
    }

    public MessageBytes remoteAddr() {
        return remoteAddrMB;
    }

    public MessageBytes remoteHost() {
        return remoteHostMB;
    }

    public MessageBytes localName() {
        return localNameMB;
    }

    public MessageBytes localAddr() {
        return localAddrMB;
    }

    public int getRemotePort(){
        return remotePort;
    }

    public void setRemotePort(int port){
        this.remotePort = port;
    }

    public int getLocalPort(){
        return localPort;
    }

    public void setLocalPort(int port){
        this.localPort = port;
    }

    // -------------------- encoding/type --------------------

    /**
     * 获取用于此请求的字符编码
     * Get the character encoding used for this request.
     *
     * @return The value set via {@link #setCharset(Charset)} or if no
     *         call has been made to that method try to obtain if from the
     *         content type.
     */
//    public String getCharacterEncoding() {
//        if (characterEncoding == null) {
//            characterEncoding = getCharsetFromContentType(getContentType());
//        }
//
//        return characterEncoding;
//    }

    /**
     * 获取用于此请求的字符编码
     * Get the character encoding used for this request.
     *
     * @return The value set via setCharset(Charset) or if no
     *         call has been made to that method try to obtain if from the
     *         content type.
     *
     * @throws UnsupportedEncodingException If the user agent has specified an
     *         invalid character encoding
     */
//    public Charset getCharset() throws UnsupportedEncodingException {
//        if (charset == null) {
//            getCharacterEncoding();
//            if (characterEncoding != null) {
//                charset = B2CConverter.getCharset(characterEncoding);
//            }
//        }
//
//        return charset;
//    }

//    public void setCharset(Charset charset) {
//        this.charset = charset;
//        this.characterEncoding = charset.name();
//    }

    public void setContentLength(long len) {
        this.contentLength = len;
    }


    public int getContentLength() {
        long length = contentLength;

        if (length < Integer.MAX_VALUE) {
            return (int) length;
        }
        return -1;
    }

    public String getContentType() {
        contentType();
        if ((contentTypeMB == null) || contentTypeMB == null) {
            return null;
        }
        return contentTypeMB.toString();
    }


    public void setContentType(String type) {
        contentTypeMB.setContent(type);
    }


    public MessageBytes contentType() {
        if (contentTypeMB == null) {
            String[] contentTypes = headers.getHeader("content-type");
            contentTypeMB.setContent(contentTypes);
        }
        return contentTypeMB;
    }


    public void setContentType(MessageBytes mb) {
        contentTypeMB=mb;
    }


    public String getHeader(String name) {
        String[] header = headers.getHeader(name);
        if(header.length == 0) {
            return "";
        }
        return headers.getHeader(name)[0];
    }

}
