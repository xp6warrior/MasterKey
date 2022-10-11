package Objects;

public class Password {
    // Password:
    // An object which stores a password and its corresponding title
    // Used to transfer data of one password-title pair between classes

    private final String title;
    private final String pass;

    public Password(String title, String pass) {
        this.title = title;
        this.pass = pass;
    }

    public String getTitle() {
        return title;
    }

    public String getPass() {
        return pass;
    }
}
