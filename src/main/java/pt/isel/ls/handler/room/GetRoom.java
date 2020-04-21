package pt.isel.ls.handler.room;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.handler.room.result.GetRoomResult;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.utils.UtilMethods.formatStringToDate;

public class GetRoom extends RoomHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection)
            throws SQLException, ParseException {
        List<Room> rooms = executeGetCommand(commandRequest, connection);
        List<List<String>> roomResult = new LinkedList<>();
        for (Room r : rooms) {
            roomResult.add(r.parsePropertiesList(false));
        }
        return new GetRoomResult(roomResult);
    }

    @Override
    public String description() {
        return "Show all rooms";
    }

    private List<Room> executeGetCommand(CommandRequest request, Connection connection)
            throws ParseException, SQLException {
        List<Room> result;
        if (!request.getParametersByName(beginParameter).isEmpty()
                && !request.getParametersByName(durationParameter).isEmpty()) {
            String begin = request.getParametersByName(beginParameter).get(0);
            String duration = request.getParametersByName(durationParameter).get(0);
            Date beginDate = formatStringToDate(begin);
            result = getAvailableRooms(connection, beginDate, BookingHandler.parseDuration(duration, beginDate));
        } else {
            result = getAllRoomsWithLabels(connection);
        }
        if (!request.getParametersByName(capacityParameter).isEmpty()) {
            int capacity = Integer.parseInt(request.getParametersByName(capacityParameter).get(0));
            result.removeIf(room -> room.getCapacity() < capacity);
        }
        if (!request.getParametersByName(labelParameter).isEmpty()) {
            List<String> labels = request.getParametersByName(labelParameter);
            for (String labelName : labels) {
                result.removeIf(room -> room.getLabels()
                        .stream().noneMatch(label -> label.getName().equals(labelName)));
            }
        }
        return result;
    }
}

