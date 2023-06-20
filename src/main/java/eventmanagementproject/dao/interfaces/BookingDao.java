package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Booking;
import java.util.List;

public interface BookingDao {
    void create(Booking booking);
    Booking get(int id);
    List<Booking> getAll();
    void update(Booking booking);
    void delete(int id);
}
