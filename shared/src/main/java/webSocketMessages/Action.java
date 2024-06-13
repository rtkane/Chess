package webSocketMessages;

public class Action {
    public enum Type {
        ENTER, EXIT, REGISTER, LOGIN, LOGOUT
    }

    private Type type;
    private String username;
    private String password;
    private String email;

    public Action(Type type) {
        this.type = type;
    }


    public Action(Type type, String username, String password, String email) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Action(Type type, String username, String password) {
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
