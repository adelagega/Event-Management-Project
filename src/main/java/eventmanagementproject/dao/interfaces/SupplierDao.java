package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Supplier;
import java.util.List;

public interface SupplierDao {
    void create(Supplier supplier);
    Supplier get(int id);
    List<Supplier> getAll();
    void update(Supplier supplier);
    void delete(int id);
}
