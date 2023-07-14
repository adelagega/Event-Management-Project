package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Invoice;
import java.util.List;

public interface InvoiceDao {
    void create(Invoice invoice);
    Invoice get(int id);
    List<Invoice> getAll();
    void update(Invoice invoice);
    void delete(int id);
}
