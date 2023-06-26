package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Root")
public class Root {
    @JsonProperty("Booking")
    private Booking booking;

    public Root(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}
