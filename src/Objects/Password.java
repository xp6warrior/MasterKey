package Objects;

public class Password {
    private final String title;
    private final String pass;

    public Password(String pass, String title) {
        this.pass = pass;
        this.title = title;
    }

    public String[] getInfo() {
        return new String[]{title, pass};
    }
}
