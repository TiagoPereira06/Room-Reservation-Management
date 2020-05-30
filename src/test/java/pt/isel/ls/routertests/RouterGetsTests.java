package pt.isel.ls.routertests;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.LocalInterface;
import pt.isel.ls.handler.booking.getall.GetBooking;
import pt.isel.ls.handler.label.getall.GetLabel;
import pt.isel.ls.handler.room.getall.GetRoom;
import pt.isel.ls.handler.user.getall.GetUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;


public class RouterGetsTests {
    private final Router router;

    public RouterGetsTests() {
        router = new Router();
        App.initRoutes(router);
    }

    @Test
    public void routerGetUsers() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/users"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetUser);
    }

    @Test
    public void routerGetLabels() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/labels"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetLabel);
    }

    @Test
    public void routerGetBookings() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/bookings"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetBooking);
    }

    @Test
    public void routerGetRooms() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/rooms"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetRoom);
    }

    @Test
    public void routerGetRoomsWithCapacity() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/rooms", "capacity=25"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetRoom);

    }

    @Test
    public void routerGetRoomsWithLabel() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/rooms", "label=windows"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetRoom);

    }

    @Test
    public void routerGetRoomsWithTime() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/rooms", "begin=2020-04-22+12:00:00"
                + "&duration=60"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetRoom);

    }

}
