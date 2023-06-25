package eventmanagementproject.entity;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name="Booking")
@XmlAccessorType(XmlAccessType.FIELD)
public class Booking {
    @XmlElement(name="bookingId")
    private int bookingId;
    @XmlElement(name="paymentStatus")
    private String paymentStatus;
    @XmlElement(name="Event")
    private Event event;
    @XmlElementWrapper(name="Clients")
    @XmlElement(name="Client")
    private List<Client> clients;
    @XmlElement(name="Payment")
    private Payment payment;
    public Booking() {
    }

    public Booking(int bookingId, String paymentStatus, Event event, List<Client> clients, Payment payment) {
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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
