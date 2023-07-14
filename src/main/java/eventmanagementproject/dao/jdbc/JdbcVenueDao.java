package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.VenueDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Venue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcVenueDao extends AbstractDao<Venue> implements VenueDao {
    private final static Logger logger = LoggerFactory.getLogger(JdbcVenueDao.class);

    @Override
    public Venue get(int id) {
        try (PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM venues WHERE venue_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Venue(rs.getInt("venue_id"), rs.getString("venue_name"), rs.getInt("capacity"), rs.getString("location"));
            } else {
                throw new DaoException("Venue with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching venue with id{}", id, e);
            throw new DaoException("Failed to fetch venue with id" + id, e);
        }
    }

    @Override
    public List<Venue> getAll() {
        List<Venue> venues = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM venues";
        try (PreparedStatement ps = this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                venues.add(new Venue(rs.getInt("venue_id"), rs.getString("venue_name"), rs.getInt("capacity"), rs.getString("location")));
            }
            return venues;
        } catch (SQLException e) {
            logger.error("Error fetching all venues", e);
            throw new DaoException("Failed to fetch all venues", e);
        }
    }

    @Override
    public void create(Venue venue) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO venues(venue_name, capacity, location) values(?,?,?)")) {
            ps.setString(1, venue.getVenueName());
            ps.setInt(2, venue.getCapacity());
            ps.setString(3, venue.getLocation());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting venue", e);
            throw new DaoException("Failed to insert venue", e);
        }
    }

    @Override
    public void update(Venue venue) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE venues SET venue_name=?, capacity=?, location=? where venue_id=?")) {
            ps.setString(1, venue.getVenueName());
            ps.setInt(2, venue.getCapacity());
            ps.setString(3, venue.getLocation());
            ps.setInt(4, venue.getVenueId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating venue with id {}", venue.getVenueId(), e);
            throw new DaoException("Failed to update venue", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM venues WHERE venue_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting venue with id {}", id, e);
            throw new DaoException("Failed to delete venue with id " + id, e);
        }
    }
}
