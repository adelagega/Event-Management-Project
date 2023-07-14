package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Venue;
import java.util.List;

public interface VenueDao {
    void create(Venue venue);
    Venue get(int id);
    List<Venue> getAll();
    void update(Venue venue);
    void delete(int id);
}
