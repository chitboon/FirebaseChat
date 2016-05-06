package sg.edu.nyp.firebasechat;
/**
 * Created by chitboon on 5/6/16.
 */
public class Message {
    String message;
    String name;
    public Message() {
    }

    public Message(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
