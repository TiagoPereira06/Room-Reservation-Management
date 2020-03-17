package pt.isel.ls.model;

public class Label {
    private String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Label Info: %s \n", name);
    }
}
