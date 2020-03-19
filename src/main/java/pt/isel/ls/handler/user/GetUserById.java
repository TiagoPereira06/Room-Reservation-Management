package pt.isel.ls.handler.user;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserById implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {

        CommandResult commandResult = new CommandResult();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getUsersByIdQuery = "SELECT * "
                    + "FROM users"
                    + "WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commandResult.getResult().add(new Booking(resultSet.getString("reservationOwner"), resultSet.getString("roomName"),resultSet.getString("beginTime"),resultSet.getString("endTime")));
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return commandResult;
    }

    @Override
    public String description() {
        return "Show users with id";
    }


}