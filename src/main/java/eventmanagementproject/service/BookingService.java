package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.BookingDao;
import eventmanagementproject.dao.interfaces.PaymentDao;
import eventmanagementproject.entity.Booking;
import eventmanagementproject.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookingService {
    private final static Logger logger = LoggerFactory.getLogger(BookingService.class);
    private final BookingDao bookingDao;
    private final PaymentDao paymentDao;

    public BookingService(BookingDao bookingDao, PaymentDao paymentDao) {
        this.bookingDao = bookingDao;
        this.paymentDao = paymentDao;
    }

    public Booking getBooking(int id) {
        try {
            return bookingDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving booking with id {}", id, e);
            throw e;
        }
    }

    public List<Booking> getAllBookings() {
        try {
            return bookingDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all bookings", e);
            throw e;
        }
    }

    public void createBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        try {
            bookingDao.create(booking);
        } catch (DaoException e) {
            logger.error("Error saving booking", e);
            throw e;
        }
    }

    public void updateBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        try {
            bookingDao.update(booking);
        } catch (DaoException e) {
            logger.error("Error updating booking with id {}", booking.getBookingId(), e);
            throw e;
        }
    }

    public void deleteBooking(int id) {
        List<Payment> payments = paymentDao.findByBookingId(id);
        if (!payments.isEmpty()) {
            throw new IllegalStateException("Cannot delete booking as there are associated payments");
        }
        try {
            bookingDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting booking with id {}", id, e);
            throw e;
        }
    }
}
