package Objects;

public class Term {
    // Term:
    // An object which stores the name of a single term
    // Used to transfer data of one term between classes

    private final String name;

    public Term (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
