package pt.isel.ls.handler.room.getall;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.model.Room;

import java.util.List;

public class GetRoomView extends View {
    private final List<Room> model;

    public GetRoomView(List<Room> allRooms) {
        this.model = allRooms;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String htmlOutput() {
        return null;
    }

    @Override
    public String plainOutput() {
        return null;
    }
}