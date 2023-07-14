package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventSupplierAssociationDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.EventSupplierAssociation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventSupplierAssociationDao extends AbstractDao<EventSupplierAssociation> implements EventSupplierAssociationDao {
    private final static Logger logger = LoggerFactory.getLogger(JdbcEventSupplierAssociationDao.class);

    @Override
    public EventSupplierAssociation get(int id) {
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM event_suppliers WHERE event_id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EventSupplierAssociation(rs.getInt("event_id"), rs.getInt("supplier_id"));
            } else {
                throw new DaoException("EventSupplierAssociation with event_id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching EventSupplierAssociation with id{}", id, e);
            throw new DaoException("Failed to fetch EventSupplierAssociation with id" + id, e);
        }
    }

    @Override
    public List<EventSupplierAssociation> getAll() {
        List<EventSupplierAssociation> eventSupplierAssociationList = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM event_suppliers")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                eventSupplierAssociationList.add(new EventSupplierAssociation(rs.getInt("event_id"), rs.getInt("supplier_id")));
            }
            return eventSupplierAssociationList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all EventStaffAssociation");
        }
    }

    @Override
    public void create(EventSupplierAssociation eventSupplierAssociation) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO event_suppliers (event_id, supplier_id) VALUES (?, ?)")) {
            ps.setInt(1, eventSupplierAssociation.getEventId());
            ps.setInt(2, eventSupplierAssociation.getSupplierId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert eventSupplierAssociation", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM event_suppliers WHERE event_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete eventStaffAssociation", e);
        }
    }
}
