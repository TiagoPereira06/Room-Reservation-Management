package pt.isel.ls.handler.booking.getbyowner;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.model.Booking;

import java.util.List;

public class GetBookingByOwnerView extends View {
    private final List<Booking> model;

    public GetBookingByOwnerView(List<Booking> ownerBookings) {
        this.model = ownerBookings;

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