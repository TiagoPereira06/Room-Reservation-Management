package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;


public interface CommandHandler {

    ResultView execute(CommandRequest commandRequest, Connection connection) throws Exception;

    String description();

}
