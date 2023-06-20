package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventStaffAssociationDao;
import eventmanagementproject.entity.EventStaffAssociation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EventStaffAssociationService {
    private final static Logger logger = LoggerFactory.getLogger(EventStaffAssociationService.class);
    private final EventStaffAssociationDao eventStaffAssociationDao;

    public EventStaffAssociationService(EventStaffAssociationDao eventStaffAssociationDao) {
        this.eventStaffAssociationDao = eventStaffAssociationDao;
    }

    public EventStaffAssociation getEventStaffAssociation(int id) {
        try {
            return eventStaffAssociationDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving EventStaffAssociation with id {}", id, e);
            throw e;
        }
    }

    public List<EventStaffAssociation> getAllEventStaffAssociation() {
        try {
            return eventStaffAssociationDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all EventStaffAssociations", e);
            throw e;
        }
    }

    public void createEventStaffAssociation(EventStaffAssociation eventStaffAssociation) {
        if (eventStaffAssociation == null) {
            throw new IllegalArgumentException("EventStaffAssociation cannot be null");
        }
        try {
            eventStaffAssociationDao.create(eventStaffAssociation);
        } catch (DaoException e) {
            logger.error("Error saving EventStaffAssociation", e);
            throw e;
        }
    }

    public void deleteEventStaffAssociation(int id) {
        try {
            eventStaffAssociationDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting EventStaffAssociation with id {}", id, e);
            throw e;
        }
    }
}

