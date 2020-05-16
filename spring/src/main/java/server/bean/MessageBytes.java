package server.bean;

/**
 * @author jianming
 * @create 2020-05-16-17:11
 */
public class MessageBytes<T> {

    private T content;

    public static MessageBytes newInstance() {
        return new MessageBytes();
    }

    public MessageBytes() {
    }

    public MessageBytes(T content) {
        this.content = content;
    }

    public void setContent(T content) {
        if(content != null) {
            this.content = content;
        }
    }

    public T getContent() {
        return content;
    }
}
