package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.BookingDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookingDao extends AbstractDao<Booking> implements BookingDao {

    private final static Logger logger = LoggerFactory.getLogger(JdbcBookingDao.class);

    @Override
    public Booking get(int id) {
        try (PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM bookings WHERE booking_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Booking(rs.getInt("booking_id"), rs.getInt("client_id"), rs.getInt("event_id"), rs.getString("payment_status"));
            } else {
                throw new DaoException("Booking with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching booking with id{}", id, e);
            throw new DaoException("Failed to fetch booking with id" + id, e);
        }
    }

    @Override
    public List<Booking> getAll() {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM bookings")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(rs.getInt("booking_id"), rs.getInt("client_id"), rs.getInt("event_id"), rs.getString("payment_status")));
            }
            return bookings;
        } catch (SQLException e) {
            logger.error("Error fetching all bookings", e);
            throw new DaoException("Failed to fetch all bookings", e);
        }
    }

    @Override
    public void create(Booking booking) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO bookings (client_id, event_id, payment_status) VALUES (?, ?, ?)")) {
            ps.setInt(1, booking.getClientId());
            ps.setInt(2, booking.getEventId());
            ps.setString(3, booking.getPaymentStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting booking", e);
            throw new DaoException("Failed to insert booking", e);
        }
    }

    @Override
    public void update(Booking booking) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE bookings SET client_id=?, event_id=?, payment_status=? WHERE booking_id = ?")) {
            ps.setInt(1, booking.getClientId());
            ps.setInt(2, booking.getEventId());
            ps.setString(3, booking.getPaymentStatus());
            ps.setInt(4, booking.getBookingId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating booking with id {}", booking.getBookingId(), e);
            throw new DaoException("Failed to update booking", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM bookings WHERE booking_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting booking with id {}", id, e);
            throw new DaoException("Failed to delete booking with id " + id, e);
        }
    }
}

