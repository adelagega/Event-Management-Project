package entity;

public class EventStaffAssociation {
   private int eventId;
   private int staffId;

    public EventStaffAssociation(int eventId, int staffId) {
        this.eventId = eventId;
        this.staffId = staffId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
