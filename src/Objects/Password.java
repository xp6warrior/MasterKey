package Objects;

public class Password {
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
