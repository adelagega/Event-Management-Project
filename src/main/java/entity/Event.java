package entity;

import JAXB.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
    @XmlElement(name="eventId")
    private int eventId;
    @XmlElement(name="eventDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate eventDate;
    @XmlElement(name="budget")
    private float budget;
    @XmlElement(name="theme")
    private String theme;

    public Event() {
    }

    public Event(int eventId, LocalDate eventDate, float budget, String theme) {
        this.eventId = eventId;
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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
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
                ", eventDate=" + eventDate +
                ", budget=" + budget +
                ", theme='" + theme + '\'' +
                '}';
    }
}



