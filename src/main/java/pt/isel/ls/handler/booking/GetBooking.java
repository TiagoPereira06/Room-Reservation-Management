package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;


public class GetBooking implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public String description() {
        return "Show all bookings";
    }
}
