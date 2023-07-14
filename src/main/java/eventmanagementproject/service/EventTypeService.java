package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventTypeDao;
import eventmanagementproject.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EventTypeService {
    private final static Logger logger = LoggerFactory.getLogger(EventTypeService.class);
    private final EventTypeDao eventTypeDao;

    public EventTypeService(EventTypeDao eventTypeDao) {
        this.eventTypeDao = eventTypeDao;
    }

    public EventType getEventType(int id) {
        try {
            return eventTypeDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving EventType with id {}", id, e);
            throw e;
        }
    }

    public List<EventType> getAllEventTypes() {
        try {
            return eventTypeDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all EventTypes", e);
            throw e;
        }
    }
}
