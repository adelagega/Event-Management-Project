package eventmanagementproject.entity;

public class EventSupplierAssociation {
    private int eventId;
    private int supplierId;

    public EventSupplierAssociation(int eventId, int supplierId) {
        this.eventId = eventId;
        this.supplierId = supplierId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "EventSupplierAssociation{" +
                "eventId=" + eventId +
                ", supplierId=" + supplierId +
                '}';
    }
}
