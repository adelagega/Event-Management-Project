package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Payment;
import java.util.List;

public interface PaymentDao {
    void create(Payment payment);
    Payment get(int id);
    List<Payment> getAll();
    void update(Payment payment);
    List<Payment> findByBookingId(int id);
}
