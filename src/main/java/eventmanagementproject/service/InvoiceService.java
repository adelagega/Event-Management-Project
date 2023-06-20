package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.InvoiceDao;
import eventmanagementproject.entity.Booking;
import eventmanagementproject.entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InvoiceService {
    private final InvoiceDao invoiceDao;
    private final static Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public Invoice getInvoice(int id) {
        try {
            return invoiceDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving invoice with id {}", id, e);
            throw e;
        }
    }

    public List<Invoice> getAllInvoices() {
        try {
            return invoiceDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all invoices", e);
            throw e;
        }
    }

    public void createInvoice(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice cannot be null");
        }
        try {
            invoiceDao.create(invoice);
        } catch (DaoException e) {
            logger.error("Error saving invoice", e);
            throw e;
        }
    }

    public void updateInvoice(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice cannot be null");
        }
        try {
            invoiceDao.update(invoice);
        } catch (DaoException e) {
            logger.error("Error updating booking with id {}", invoice.getInvoiceId(), e);
            throw e;
        }
    }

    public void deleteInvoice(int id) {
        try {
            invoiceDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting invoice with id {}", id, e);
            throw e;
        }
    }
}

