package pt.isel.ls.handler.room.post;

import pt.isel.ls.handler.View;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostRoomView extends View {
    private final String model;

    public PostRoomView(String roomName) {
        this.model = roomName;
    }

    @Override
    public String name() {
        return model + " room posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("room id: " + model))

        ).build();
    }

    @Override
    public String plainOutput() {
        return model;
    }

}

