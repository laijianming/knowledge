package server.servlet.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * request 的顶级接口，包含一些关键的方法
 * @author jianming
 * @create 2020-05-15-18:13
 */
public interface ServletRequest {

    /**
     * 获取attribute
     * @param name
     * @return
     */
    Object getAttribute(String name);

    /**
     * 获取Attribute的name的集合
     * @return
     */
    Enumeration<String> getAttributeNames();

    /**
     * 获取请求报文编码格式
     * @return
     */
    String getCharacterEncoding();

    /**
     * 设置请求报文编码格式
     * @param env
     * @throws java.io.UnsupportedEncodingException
     */
    void setCharacterEncoding(String env)
            throws java.io.UnsupportedEncodingException;

    /**
     * 返回请求正文的长度（字节），由输入流提供，如果长度未知，则返回-1。
     * @return
     */
    int getContentLength();

    /**
     * 返回请求正文的长度（字节），由输入流提供，如果长度未知，则返回-1。
     * @return
     */
    long getContentLengthLong();

    /**
     * 返回请求正文的MIME类型，如果*类型未知，则返回<code>null</code>。
     * @return
     */
    String getContentType();

    /**
     * 使用 ServletInputStream 将请求的主体作为二进制数据检索。
     * 可以调用此方法或 #getReader 来读取主体，而不是同时调用两者。
     * @return
     * @throws IOException
     */
    //ServletInputStream getInputStream() throws IOException;

    /**
     * 将请求参数的值返回为<code>字符串</code>，如果该参数不存在，则返回<code>空值</code>。
     * 请求参数是随请求一起发送的额外信息。对于httpservlet，参数包含在查询字符串或发布的表单数据中。
     */
    String getParameter(String name);

    /**
     * 返回包含此请求中包含的参数名称的<code>枚举<code>字符串<code>对象。
     * 如果请求没有参数，则该方法返回空的<code>枚举</code>。
     * @return
     */
    Enumeration<String> getParameterNames();

    /**
     * 返回一个<code>String</code>对象数组，其中包含给定请求参数所具有的所有值，
     * 如果参数不存在，则返回<code>null</code>。
     * 如果参数只有一个值，则数组的长度为1。
     * @param name
     * @return
     */
    String[] getParameterValues(String name);

    /**
     * 返回此请求的参数的java.util.Map。请求参数是随请求发送的额外信息。
     * 对于HTTP jmservlet，参数包含在查询字符串或发布的form data中。
     * @return 返回一个map，key为参数名，value为参数值
     */
    Map<String, String[]> getParameterMap();

    /**
     * 返回请求使用的协议的名称和版本，格式为<i>protocol/majorVersion.minorVersion</i>，
     * 例如HTTP/1.1。对于HTTP servlets，返回的值与CGI变量<code>服务器协议的值相同。
     * @return
     */
    String getProtocol();

    /**
     * 返回用于发出此请求的方案的名称，例如，<code>http</code>，<code>https</code>或<code>ftp</code>。
     * 不同的方案对于构建URL有不同的规则，如RFC1738所述。
     * @return
     */
    String getScheme();

    /**
     * 返回向其发送请求的服务器的主机名。它是前面部分的值“：”在<code>主机</code>头值中，
     * 如果有的话，或解析的服务器名或服务器IP地址。
     * @return
     */
    String getServerName();

    /**
     * 获取服务器的主机端口
     * @return
     */
    int getServerPort();

    /**
     * 使用<code>BufferedReader</code>检索请求的正文作为字符数据。
     * 读取器根据主体上使用的字符编码来翻译字符数据。可以调用此方法或getInputStream来读取主体，而不是同时读取两者。
     * @return
     * @throws IOException
     */
    BufferedReader getReader() throws IOException;

    /**
     * 返回发送请求的客户端或最后一个代理的Internet协议（IP）地址。
     * 对于HTTP jmservlet，与CGI变量的值相同。
     * @return
     */
    String getRemoteAddr();

    /**
     * 返回发送请求的客户端或最后一个代理的完全限定名。
     * 如果引擎无法或选择不解析主机名（以提高性能），则此方法返回IP地址的虚线字符串形式。
     * 对于HTTP servlets，与CGI变量的值相同。
     * @return
     */
    String getRemoteHost();

    /**
     * 添加一个属性到request中
     * @param name
     * @param o
     */
    void setAttribute(String name, Object o);

    /**
     * 删除一个request中的属性
     * @param name
     */
    void removeAttribute(String name);

    /**
     * 返回首选的<code>区域设置</code>，客户端将根据accept Language头接受其中的内容。
     * 如果客户端请求不提供接受语言头，则此方法返回服务器的默认区域设置。
     * @return
     */
    Locale getLocale();

    /**
     * 返回区域设置的枚举类
     * @return
     */
    Enumeration<Locale> getLocales();

    /**
     * 返回一个布尔值，指示此请求是否使用安全通道（如HTTPS）发出。
     * @return
     */
    boolean isSecure();

    /**
     * 获取远程客户端主机端口
     * @return
     */
    int getRemotePort();

    /**
     * 返回接收请求的Internet协议（IP）接口的主机名。
     * @return
     */
    String getLocalName();

    /**
     * 返回接收请求的接口的Internet协议（IP）地址。
     * @return
     */
    String getLocalAddr();

    /**
     * 返回接收请求的接口的Internet协议（IP）端口号。
     * @return
     */
    int getLocalPort();
}
