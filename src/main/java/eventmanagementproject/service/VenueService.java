package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.VenueDao;
import eventmanagementproject.entity.Venue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VenueService {
    private final static Logger logger = LoggerFactory.getLogger(VenueService.class);
    private final VenueDao venueDao;

    public VenueService(VenueDao venueDao) {
        this.venueDao = venueDao;
    }

    public Venue getVenue(int id) {
        try {
            return venueDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving venue with id {}", id, e);
            throw e;
        }
    }

    public List<Venue> getAllVenues() {
        try {
            return venueDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all venues", e);
            throw e;
        }
    }

    public void createVenue(Venue venue) {
        if (venue == null) {
            throw new IllegalArgumentException("Venue cannot be null");
        }
        try {
            venueDao.create(venue);
        } catch (DaoException e) {
            logger.error("Error saving venue", e);
            throw e;
        }
    }

    public void updateVenue(Venue venue) {
        if (venue == null) {
            throw new IllegalArgumentException("Venue cannot be null");
        }
        try {
            venueDao.update(venue);
        } catch (DaoException e) {
            logger.error("Error updating venue with id {}", venue.getVenueId(), e);
            throw e;
        }
    }

    public void deleteVenue(int id) {
        try {
            venueDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting venue with id {}", id, e);
            throw e;
        }
    }
}
