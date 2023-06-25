package entity;

public class EventType {
    private int eventTypeId;
    private String eventTypeName;

    public EventType(int eventTypeId, String eventTypeName) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
}
