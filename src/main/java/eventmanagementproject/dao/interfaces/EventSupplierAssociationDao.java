package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.EventSupplierAssociation;
import java.util.List;

public interface EventSupplierAssociationDao {
    void create(EventSupplierAssociation eventSupplierAssociation);
    EventSupplierAssociation get(int id);
    List<EventSupplierAssociation> getAll();
    void delete(int id);
}
