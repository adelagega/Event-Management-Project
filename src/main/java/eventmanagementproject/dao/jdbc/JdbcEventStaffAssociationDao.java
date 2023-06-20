package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventStaffAssociationDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.EventStaffAssociation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventStaffAssociationDao extends AbstractDao<EventStaffAssociation> implements EventStaffAssociationDao {

    private final static Logger logger = LoggerFactory.getLogger(JdbcEventStaffAssociationDao.class);

    @Override
    public EventStaffAssociation get(int id) {
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM event_staff WHERE event_id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EventStaffAssociation(rs.getInt("event_id"), rs.getInt("staff_id"));
            } else {
                throw new DaoException("EventStaffAssociation with event_id" + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching EventStaffAssociation with id{}", id, e);
            throw new RuntimeException("Failed to fetch EventStaffAssociation with event_id: " + id, e);
        }
    }

    @Override
    public List<EventStaffAssociation> getAll() {
        List<EventStaffAssociation> eventStaffAssociationList = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM event_staff")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                eventStaffAssociationList.add(new EventStaffAssociation(rs.getInt("event_id"), rs.getInt("staff_id")));
            }
            return eventStaffAssociationList;
        } catch (SQLException e) {
            logger.error("Error fetching all eventSupplierAssociations", e);
            throw new DaoException("Failed to fetch all eventSupplierAssociation", e);
        }
    }

    @Override
    public void create(EventStaffAssociation eventStaffAssociation) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO event_staff (event_id, staff_id) VALUES (?,?)")) {
            ps.setInt(1, eventStaffAssociation.getEventId());
            ps.setInt(2, eventStaffAssociation.getStaffId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting eventSupplierAssociation", e);
            throw new DaoException("Failed to insert eventSupplierAssociation", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM event_staff WHERE event_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting eventSupplierAssociation", e);
            throw new DaoException("Failed to delete eventSupplierAssociation", e);
        }
    }
}
