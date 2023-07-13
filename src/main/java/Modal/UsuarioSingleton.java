package Modal;

public class UsuarioSingleton {
    private static Usuario connection;

    private String username;
    private String password;

    private void Usuario() {
    }

    public static Usuario getConnection() {
        if (connection == null) {
            connection = new Usuario();
        }
        return connection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}