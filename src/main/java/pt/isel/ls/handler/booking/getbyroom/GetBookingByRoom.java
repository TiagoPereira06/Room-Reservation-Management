package pt.isel.ls.handler.booking.getbyroom;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class GetBookingByRoom extends BookingHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws Exception {
        String getBookingsByRoomQuery = "SELECT * FROM bookings WHERE roomname = ?";
        PreparedStatement statement = connection.prepareStatement(getBookingsByRoomQuery);
        statement.setString(1, commandRequest.getParametersByName(roomIdArgument).get(0));
        ResultSet resultSet = statement.executeQuery();
        List<Booking> roomBookings = new LinkedList<>();
        while (resultSet.next()) {
            Booking b = new Booking(resultSet.getString("reservationOwner"),
                    resultSet.getString("roomName"),
                    resultSet.getString("beginTime"),
                    resultSet.getString("endTime"));
            b.setId(resultSet.getInt("bid"));
            roomBookings.add(b);
        }
        return new GetBookingByRoomView(roomBookings);
    }

    @Override
    public String description() {
        return "Shows the list of all bookings at rid room";
    }
}
