package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.EventSupplierAssociationDao;
import eventmanagementproject.entity.EventSupplierAssociation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EventSupplierAssociationService {
    private final static Logger logger = LoggerFactory.getLogger(EventSupplierAssociationService.class);
    private final EventSupplierAssociationDao eventSupplierAssociationDao;

    public EventSupplierAssociationService(EventSupplierAssociationDao eventSupplierAssociationDao) {
        this.eventSupplierAssociationDao = eventSupplierAssociationDao;
    }

    public EventSupplierAssociation getEventSupplierAssociation(int id) {
        try {
            return eventSupplierAssociationDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving EventSupplierAssociation with id {}", id, e);
            throw e;
        }
    }

    public List<EventSupplierAssociation> getAllEventSupplierAssociation() {
        try {
            return eventSupplierAssociationDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all EventSupplierAssociations", e);
            throw e;
        }
    }

    public void createEventSupplierAssociation(EventSupplierAssociation eventSupplierAssociation) {
        if (eventSupplierAssociation == null) {
            throw new IllegalArgumentException("EventSupplierAssociation cannot be null");
        }
        try {
            eventSupplierAssociationDao.create(eventSupplierAssociation);
        } catch (DaoException e) {
            logger.error("Error saving EventSupplierAssociation", e);
            throw e;
        }
    }

    public void deleteEventSupplierAssociation(int id) {
        try {
            eventSupplierAssociationDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting EventSupplierAssociation with id {}", id, e);
            throw e;
        }
    }
}
