package pt.isel.ls.routertests;

import junit.framework.Assert;

import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.LocalInterface;
import pt.isel.ls.handler.booking.post.PostBooking;
import pt.isel.ls.handler.label.post.PostLabel;
import pt.isel.ls.handler.room.post.PostRoom;
import pt.isel.ls.handler.user.post.PostUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

public class RouterPostsTests {
    private Router router;

    public RouterPostsTests() {
        router = new Router();
        App.initRoutes(router);
    }

    @Test
    public void routerPostUser() throws NoSuchMethodException {
        String[] rawTask = {"POST", "/users", "name=Haris+Seferovic&email=haris@slb.pt"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask,new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof PostUser);
    }

    @Test
    public void routerPostLabel() throws NoSuchMethodException {
        String[] rawTask = {"POST", "/labels", "name=slow+internet"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask,new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof PostLabel);
    }

    @Test
    public void routerPostRoom() throws NoSuchMethodException {
        String[] rawTask = {"POST", "/rooms", "name=LGO"
                + "&description=muitobom"
                + "&location=Building+F+floor+-1"
                + "&capacity=55"
                + "&label=monitors&label=windows"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask,new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof PostRoom);
    }

    @Test
    public void routerPostBooking() throws NoSuchMethodException {
        String[] rawTask = {"POST", "/rooms/LS1/bookings", "uid=ttavares@slb.pt"
                + "&begin=2020-04-08+08:30:00"
                + "&duration=45"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask,new LocalInterface());
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof PostBooking);
    }

}
