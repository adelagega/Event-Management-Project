package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Event;
import java.util.List;

public interface EventDao {
    void create(Event event);
    Event get(int id);
    List<Event> getAll();
    void update(Event event);
    void delete(int id);
}
