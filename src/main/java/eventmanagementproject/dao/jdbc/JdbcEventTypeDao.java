package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventTypeDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventTypeDao extends AbstractDao<EventType> implements EventTypeDao {

    private final static Logger logger = LoggerFactory.getLogger(JdbcEventTypeDao.class);

    @Override
    public EventType get(int id) {
        try (PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM event_types WHERE event_type_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new EventType(rs.getInt("event_type_id"),
                        rs.getString("event_type_name"));
            } else {
                throw new DaoException("Event type with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching event type with id{}", id, e);
            throw new DaoException("Failed to fetch event type with id" + id, e);
        }
    }

    @Override
    public List<EventType> getAll() {
        List<EventType> eventTypes = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM event_types")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                eventTypes.add(new EventType(rs.getInt("event_type_id"),
                        rs.getString("event_type_name")));
            }
            return eventTypes;
        } catch (SQLException e) {
            logger.error("Error fetching all event types", e);
            throw new DaoException("Failed to fetch all event types", e);
        }
    }
}

