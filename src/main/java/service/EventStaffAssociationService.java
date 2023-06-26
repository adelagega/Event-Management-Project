package service;

import dao.GenericDao;
import entity.EventStaffAssociation;
import java.util.List;

public class EventStaffAssociationService implements GenericService<EventStaffAssociation>{

    private final GenericDao<EventStaffAssociation> eventStaffAssociationDao;

    public EventStaffAssociationService(GenericDao<EventStaffAssociation> eventStaffAssociationDao) {
        this.eventStaffAssociationDao = eventStaffAssociationDao;
    }

    @Override
    public EventStaffAssociation get(int id) {
        return eventStaffAssociationDao.get(id);
    }

    @Override
    public List<EventStaffAssociation> getAll() {
        return eventStaffAssociationDao.getAll();
    }

    @Override
    public void save(EventStaffAssociation eventStaffAssociation) {
       eventStaffAssociationDao.save(eventStaffAssociation);
    }

    @Override
    public void update(EventStaffAssociation eventStaffAssociation, String[] params) {

    }

    @Override
    public void delete(EventStaffAssociation eventStaffAssociation) {
      eventStaffAssociationDao.delete(eventStaffAssociation);
    }
}
