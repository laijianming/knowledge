package pojo;

import java.io.Serializable;

/**
 * 需要远程调用使用的pojo需要实现序列化
 *  使其可序列化后进行远程传输
 * @author jianming
 * @create 2020-03-10-18:21
 */
public class Hello implements Serializable {
    String msg;
    Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Hello(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public Hello() {
    }

    @Override
    public String toString() {
        return "Hello{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
