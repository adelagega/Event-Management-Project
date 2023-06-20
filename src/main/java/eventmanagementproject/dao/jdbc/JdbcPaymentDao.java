package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.PaymentDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPaymentDao extends AbstractDao<Payment> implements PaymentDao {
    private final static Logger logger = LoggerFactory.getLogger(JdbcPaymentDao.class);

    @Override
    public Payment get(int id) {
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM payments WHERE payment_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Payment(rs.getInt("payment_id"), rs.getInt("booking_id"), rs.getFloat("amount"), rs.getDate("payment_date"), rs.getString("payment_type"));
            } else {
                throw new DaoException("Payment with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching payment with id{}", id, e);
            throw new DaoException("Failed to fetch payment with id" + id, e);
        }
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM payments")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(rs.getInt("payment_id"), rs.getInt("booking_id"), rs.getFloat("amount"), rs.getDate("payment_date"), rs.getString("payment_type")));
            }
            return payments;
        } catch (SQLException e) {
            logger.error("Error fetching all payments", e);
            throw new DaoException("Failed to fetch all payments", e);
        }
    }

    @Override
    public void create(Payment payment) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO payments (booking_id, amount, payment_date, payment_type) VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, payment.getBookingId());
            ps.setFloat(2, payment.getAmount());
            ps.setDate(3, (Date) payment.getPaymentDate());
            ps.setString(4, payment.getPaymentType());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting payment", e);
            throw new DaoException("Failed to insert payment", e);
        }
    }

    @Override
    public void update(Payment payment) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE payments SET booking_id=?, amount=?, payment_date=?, payment_type=? WHERE payment_id=?")) {
            ps.setInt(1, payment.getBookingId());
            ps.setFloat(2, payment.getAmount());
            ps.setDate(3, (Date) payment.getPaymentDate());
            ps.setString(4, payment.getPaymentType());
            ps.setInt(5, payment.getPaymentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating payment with id {}", payment.getPaymentId(), e);
            throw new DaoException("Failed to update booking", e);
        }
    }

    @Override
    public List<Payment> findByBookingId(int id) {
        List<Payment> payments = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM payments WHERE booking_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(rs.getInt("payment_id"), rs.getInt("booking_id"), rs.getFloat("amount"), rs.getDate("payment_date"), rs.getString("payment_type")));
            }
            return payments;
        } catch (SQLException e) {
            logger.error("Error fetching payments with booking id {}", id, e);
            throw new DaoException("Failed to fetch payments with booking id " + id, e);
        }
    }
}
