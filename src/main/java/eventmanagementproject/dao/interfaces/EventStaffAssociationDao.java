package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.EventStaffAssociation;
import java.util.List;

public interface EventStaffAssociationDao {
    void create(EventStaffAssociation eventStaffAssociation);
    EventStaffAssociation get(int id);
    List<EventStaffAssociation> getAll();
    void delete(int id);
}
