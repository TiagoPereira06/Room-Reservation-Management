package pt.isel.ls.handler.listen;

import pt.isel.ls.handler.result.View;

public class HttpServletView extends View {

    @Override
    public String htmlOutput() {
        return null;
    }

    @Override
    public String plainOutput() {
        return "Server Started";
    }

    @Override
    public String name() {
        return "App is now HTTP compatible";
    }

}
