package pt.isel.ls.handler.room.post.getform;

import pt.isel.ls.handler.View;
import pt.isel.ls.model.Label;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostRoomFormView extends View {
    private final List<Label> model;

    public PostRoomFormView(List<Label> model) {
        this.model = model;
    }


    @Override
    public String name() {
        return "Room Creator";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name())),
                        nav(setNavBar())
                ),
                body(
                        h1(text(name())),
                        form(
                                div(
                                        label(text("Name ")).addAttribute("for", "name"),
                                        input()
                                                .addAttribute("type", "text")
                                                .addAttribute("name", "name")
                                                .addAttribute("id", "name")
                                                .addAttribute("required", "true")
                                ),
                                div(
                                        label(text("Location ")).addAttribute("for", "location"),
                                        input()
                                                .addAttribute("type", "text")
                                                .addAttribute("name", "location")
                                                .addAttribute("id", "location")
                                                .addAttribute("required", "true")
                                ),
                                div(
                                        label(text("Capacity ")).addAttribute("for", "capacity"),
                                        input()
                                                .addAttribute("type", "number")
                                                .addAttribute("name", "capacity")
                                                .addAttribute("id", "capacity")
                                                .addAttribute("required", "true")
                                ),
                                div(
                                        label(text("Description ")).addAttribute("for", "description"),
                                        input()
                                                .addAttribute("type", "text")
                                                .addAttribute("name", "description")
                                                .addAttribute("id", "description")
                                                .addAttribute("required", "true")
                                ),
                                div(
                                        getLabelsCheckBoxes()
                                ),
                                input().addAttribute("type", "submit").addAttribute("value", "Search!")

                        ).addAttribute("method", "get")
                                .addAttribute("action", "/rooms")
                )

        ).build();
    }

    private Node setNavBar() {
        return homeButton();
    }

    private Node[] getLabelsCheckBoxes() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(text("Available Labels: "));
        for (Label l : model) {
            Node input = input()
                    .addAttribute("type", "checkbox")
                    .addAttribute("name", "label")
                    .addAttribute("id", "label")
                    .addAttribute("value", l.getName());
            Node label = label(text(l.getName())).addAttribute("for", "label");
            nodes.add(input);
            nodes.add(label);
        }
        return nodes.toArray(new Node[0]);
    }


    @Override
    public String plainOutput() {
        return "Only Available on HTML Support";
    }

}
