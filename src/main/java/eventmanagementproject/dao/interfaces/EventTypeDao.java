package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.EventType;
import java.util.List;

public interface EventTypeDao {
    EventType get(int id);
    List<EventType> getAll();
}
