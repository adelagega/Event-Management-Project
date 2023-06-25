package entity;

public class Booking {
    private int bookingId;
    private int clientId;
    private int eventId;
    private String paymentStatus;

    public Booking(int bookingId, int clientId, int eventId, String paymentStatus) {
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.eventId = eventId;
        this.paymentStatus = paymentStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
