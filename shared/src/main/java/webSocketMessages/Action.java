package webSocketMessages;

public class Action {
    public enum Type {
        ENTER,
        REGISTER,
        // other action types
    }

    private Type type;
    private String username;
    private String password;
    private String email;

    public Action(Type type, String username, String password, String email) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Action(Type type, String username) {
        this.type = type;
        this.username = username;
    }

    // Getters and setters
}
