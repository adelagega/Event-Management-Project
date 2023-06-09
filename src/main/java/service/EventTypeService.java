package service;

import dao.GenericDao;
import entity.EventType;

import java.util.List;

public class EventTypeService implements GenericService<EventType>{

    private final GenericDao<EventType> eventTypeDao;

    public EventTypeService(GenericDao<EventType> eventTypeDao) {
        this.eventTypeDao = eventTypeDao;
    }

    @Override
    public EventType get(int id) {
        return eventTypeDao.get(id);
    }

    @Override
    public List<EventType> getAll() {
        return eventTypeDao.getAll();
    }

    @Override
    public void save(EventType eventType) {
      eventTypeDao.save(eventType);
    }

    @Override
    public void update(EventType eventType, String[] params) {
     eventTypeDao.update(eventType,params);
    }

    @Override
    public void delete(EventType eventType) {
     eventTypeDao.delete(eventType);
    }
}
