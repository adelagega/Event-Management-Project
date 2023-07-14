package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventDao extends AbstractDao<Event> implements EventDao {
    private final static Logger logger = LoggerFactory.getLogger(JdbcEventDao.class);

    @Override
    public void create(Event event) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO events (event_type_id, venue_id, event_date, budget, theme) VALUES (?, ?, ?, ?, ?)")) {
            ps.setInt(1, event.getEventTypeId());
            ps.setInt(2, event.getVenueId());
            ps.setDate(3, (Date) event.getEventDate());
            ps.setFloat(4, event.getBudget());
            ps.setString(5, event.getTheme());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting event", e);
            throw new DaoException("Failed to insert event", e);
        }
    }

    @Override
    public Event get(int id) {
        try (PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM events WHERE event_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Event(rs.getInt("event_id"), rs.getInt("event_type_id"), rs.getInt("venue_id"), rs.getDate("event_date"), rs.getFloat("budget"), rs.getString("theme"));
            } else {
                throw new DaoException("Event with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching event with id{}", id, e);
            throw new DaoException("Failed to fetch event with id" + id, e);
        }
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM events")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                events.add(new Event(rs.getInt("event_id"), rs.getInt("event_type_id"), rs.getInt("venue_id"), rs.getDate("event_date"), rs.getFloat("budget"), rs.getString("theme")));
            }
            return events;
        } catch (SQLException e) {
            logger.error("Error fetching all events", e);
            throw new DaoException("Failed to fetch all events", e);
        }
    }

    @Override
    public void update(Event event) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE events SET event_type_id = ?, venue_id = ?, event_date=? budget = ?, theme = ? WHERE event_id = ?")) {
            ps.setInt(1, event.getEventTypeId());
            ps.setInt(2, event.getVenueId());
            ps.setDate(3, (Date) event.getEventDate());
            ps.setFloat(4, event.getBudget());
            ps.setString(4, event.getTheme());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating event with id {}", event.getEventId(), e);
            throw new DaoException("Failed to update event", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM events WHERE event_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting event with id {}", id, e);
            throw new DaoException("Failed to delete event with id " + id, e);
        }
    }
}
