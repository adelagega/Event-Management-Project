package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventDao;
import eventmanagementproject.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EventService {
    private final static Logger logger = LoggerFactory.getLogger(EventService.class);
    private final EventDao eventDao;

    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event getEvent(int id) {
        try {
            return eventDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving event with id {}", id, e);
            throw e;
        }
    }

    public List<Event> getAllEvents() {
        try {
            return eventDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all events", e);
            throw e;
        }
    }

    public void createEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        try {
            eventDao.create(event);
        } catch (DaoException e) {
            logger.error("Error saving event", e);
            throw e;
        }
    }

    public void updateEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        try {
            eventDao.update(event);
        } catch (DaoException e) {
            logger.error("Error updating event with id {}", event.getEventId(), e);
            throw e;
        }
    }

    public void deleteEvent(int id) {
        try {
            eventDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting event with id {}", id, e);
            throw e;
        }
    }
}
