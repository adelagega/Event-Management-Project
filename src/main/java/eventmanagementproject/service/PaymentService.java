package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.PaymentDao;
import eventmanagementproject.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PaymentService {
    private final static Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public Payment getPayment(int id) {
        try {
            return paymentDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving payment with id {}", id, e);
            throw e;
        }
    }

    public List<Payment> getAllPayments() {
        try {
            return paymentDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all payments", e);
            throw e;
        }
    }

    public void createPayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }
        try {
            paymentDao.create(payment);
        } catch (DaoException e) {
            logger.error("Error saving payment", e);
            throw e;
        }
    }

    public void updatePayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }
        try {
            paymentDao.update(payment);
        } catch (DaoException e) {
            logger.error("Error updating payment with id {}", payment.getPaymentId(), e);
            throw e;
        }
    }
}
