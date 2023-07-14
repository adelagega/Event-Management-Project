package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.SupplierDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSupplierDao extends AbstractDao<Supplier> implements SupplierDao {
    private final static Logger logger = LoggerFactory.getLogger(JdbcSupplierDao.class);

    @Override
    public Supplier get(int id) {
        try (PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM suppliers WHERE supplier_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name"), rs.getString("supplier_type"), rs.getString("contact_name"), rs.getString("contact_email"), rs.getString("contact_phone"));
            } else {
                throw new DaoException("Supplier with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching supplier with id{}", id, e);
            throw new DaoException("Failed to fetch supplier with id" + id, e);
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM suppliers")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name"), rs.getString("supplier_type"), rs.getString("contact_name"), rs.getString("contact_email"), rs.getString("contact_phone")));
            }
            return suppliers;
        } catch (SQLException e) {
            logger.error("Error fetching all suppliers", e);
            throw new DaoException("Failed to fetch all suppliers", e);
        }
    }

    @Override
    public void create(Supplier supplier) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO suppliers (supplier_name, supplier_type,  contact_name, contact_email, contact_phone) VALUES (?,?,?,?,?)")) {
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getSupplierType());
            ps.setString(3, supplier.getContactName());
            ps.setString(4, supplier.getContactEmail());
            ps.setString(5, supplier.getContactPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting supplier", e);
            throw new DaoException("Failed to insert supplier", e);
        }
    }

    @Override
    public void update(Supplier supplier) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE suppliers SET supplier_name=?, supplier_type=?, contact_name=?, contact_email=?, contact_phone=? WHERE supplier_id=?")) {
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getSupplierType());
            ps.setString(3, supplier.getContactName());
            ps.setString(4, supplier.getContactEmail());
            ps.setString(5, supplier.getContactPhone());
            ps.setInt(6, supplier.getSupplierId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting supplier with id {}", supplier.getSupplierId(), e);
            throw new DaoException("Failed to delete supplier with id " + e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM suppliers WHERE supplier_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting supplier with id {}", id, e);
            throw new DaoException("Failed to delete supplier with id " + id, e);
        }
    }
}

