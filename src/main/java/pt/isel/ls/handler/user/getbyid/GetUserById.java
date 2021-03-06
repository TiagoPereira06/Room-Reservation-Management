package pt.isel.ls.handler.user.getbyid;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.InternalDataBaseException;
import pt.isel.ls.errors.command.InvalidArgumentException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.model.User;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GetUserById extends UserHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String getUsersByIdQuery = "select * from users"
                    + " as u FULL join bookings as b"
                    + " on b.reservationowner = u.email "
                    + "where u.email = ?";
            PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
            statement.setString(1, commandRequest.getParameterByName(idArgument));
            ResultSet resultSet = statement.executeQuery();
            User userResult;
            List<Booking> bookingResult = new LinkedList<>();
            resultSet.next();
            userResult = new User(
                    resultSet.getString("email"),
                    resultSet.getString("username")
            );
            try {
                getBooking(resultSet, bookingResult);
            } catch (NullPointerException e) {
                return new GetUserByIdResult(userResult, Collections.emptyList());
            }

            while (resultSet.next()) {
                try {
                    getBooking(resultSet, bookingResult);
                } catch (NullPointerException e) {
                    break;
                }

            }
            return new GetUserByIdResult(userResult, bookingResult);
        });

    }

    private void getBooking(ResultSet resultSet, List<Booking> bookingResult)
            throws InternalDataBaseException, InvalidArgumentException {
        try {
            Booking b;
            b = new Booking(resultSet.getInt("bid"), resultSet.getString("roomname"),
                    resultSet.getString("beginTime"), resultSet.getString("endTime"));
            bookingResult.add(b);
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        } catch (ParseException e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    @Override
    public String description() {
        return "Show users with id";
    }
}
