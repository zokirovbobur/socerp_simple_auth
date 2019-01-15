package bbro.socerp.ServerMessages;

public class ServerMessage {
    String messageType;
    String message;
    Long userId;

    public ServerMessage(String message, String messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public ServerMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
