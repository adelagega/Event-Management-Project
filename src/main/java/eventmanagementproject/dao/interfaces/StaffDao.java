package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Staff;
import java.util.List;

public interface StaffDao {
    void create(Staff staf);
    Staff get(int id);
    List<Staff> getAll();
    void update(Staff staff);
    void delete(int id);
}
