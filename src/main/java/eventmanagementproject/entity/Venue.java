package eventmanagementproject.entity;

public class Venue {
    private int venueId;
    private String venueName;
    private int capacity;
    private String location;

    public Venue(int venueId, String venueName, int capacity, String location) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.capacity = capacity;
        this.location = location;
    }

    public Venue(String venueName, int capacity, String location) {
        this.venueName = venueName;
        this.capacity = capacity;
        this.location = location;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", venueName='" + venueName + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                '}';
    }
}
