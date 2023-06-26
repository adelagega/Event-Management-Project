package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name="Booking")
@XmlAccessorType(XmlAccessType.FIELD)
public class Booking {
    @XmlElement(name="bookingId")
    @JsonProperty("bookingId")
    private int bookingId;
    @XmlElement(name="paymentStatus")
    @JsonProperty("paymentStatus")
    private String paymentStatus;
    @XmlElement(name="Event")
    @JsonProperty("Event")
    private Event event;
    @XmlElementWrapper(name="Clients")
    @XmlElement(name="Client")
    @JsonProperty("Clients")
    private Clients clients;
    @XmlElement(name="Payment")
    @JsonProperty("Payment")
    private Payment payment;

    public Booking() {
    }

    public Booking(int bookingId, String paymentStatus, Event event, Clients clients, Payment payment) {
        this.bookingId = bookingId;
        this.paymentStatus = paymentStatus;
        this.event = event;
        this.clients = clients;
        this.payment = payment;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", event=" + event +
                ", clients=" + clients +
                ", payment=" + payment +
                '}';
    }
}
