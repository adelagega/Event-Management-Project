package eventmanagementproject.entity;

import java.util.Date;

public class Event {
    private int eventId;
    private int eventTypeId;
    private int venueId;
    private Date eventDate;
    private float budget;
    private String theme;

    public Event(int eventId, int eventTypeId, int venueId, Date eventDate, float budget, String theme) {
        this.eventId = eventId;
        this.eventTypeId = eventTypeId;
        this.venueId = venueId;
        this.eventDate = eventDate;
        this.budget = budget;
        this.theme = theme;
    }

    public Event(int eventTypeId, int venueId, Date eventDate, float budget, String theme) {
        this.eventTypeId = eventTypeId;
        this.venueId = venueId;
        this.eventDate = eventDate;
        this.budget = budget;
        this.theme = theme;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventTypeId=" + eventTypeId +
                ", venueId=" + venueId +
                ", eventDate=" + eventDate +
                ", budget=" + budget +
                ", theme='" + theme + '\'' +
                '}';
    }
}
