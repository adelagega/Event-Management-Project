package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.InvoiceDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcInvoiceDao extends AbstractDao<Invoice> implements InvoiceDao {
    private final static Logger logger = LoggerFactory.getLogger(JdbcInvoiceDao.class);

    @Override
    public Invoice get(int id) {
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM invoices WHERE invoice_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Invoice(rs.getInt("invoice_id"), rs.getInt("booking_id"), rs.getDate("invoice_date"), rs.getFloat("total_amount"));
            } else {
                throw new DaoException("Invoice with id " + id + "not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching invoice with id{}", id, e);
            throw new DaoException("Failed to fetch invoice with id" + id, e);
        }
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM invoices")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                invoices.add(new Invoice(rs.getInt("invoice_id"), rs.getInt("booking_id"), rs.getDate("invoice_date"), rs.getFloat("total_amount")));
            }
            return invoices;
        } catch (SQLException e) {
            logger.error("Error fetching all invoices", e);
            throw new DaoException("Failed to fetch all invoices", e);
        }
    }

    @Override
    public void create(Invoice invoice) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO invoices (booking_id, invoice_date, total_amount) VALUES (?, ?, ?)")) {
            ps.setInt(1, invoice.getBookingid());
            ps.setDate(2, (Date) invoice.getInvoiceDate());
            ps.setFloat(3, invoice.getTotalAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting invoice", e);
            throw new DaoException("Failed to insert invoice", e);
        }
    }

    @Override
    public void update(Invoice invoice) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE invoices SET booking_id=?, invoice_date=?, total_amount=? WHERE invoice_id=?")) {
            ps.setInt(1, invoice.getBookingid());
            ps.setDate(2, (Date) invoice.getInvoiceDate());
            ps.setFloat(3, invoice.getTotalAmount());
            ps.setInt(4, invoice.getInvoiceId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating invoice with id {}", invoice.getInvoiceId(), e);
            throw new DaoException("Failed to update invoice", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM invoices WHERE invoice_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting invoice with id {}", id, e);
            throw new DaoException("Failed to delete invoice with id " + id, e);
        }
    }
}
