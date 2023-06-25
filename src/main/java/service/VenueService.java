package service;

import dao.GenericDao;
import entity.Venue;
import java.util.List;

public class VenueService implements GenericService<Venue>{

    private final GenericDao<Venue> venueDao;

    public VenueService(GenericDao<Venue> venueDao){
        this.venueDao=venueDao;
    }
    @Override
    public Venue get(int id) {
        return venueDao.get(id);
    }

    @Override
    public List<Venue> getAll() {
        return venueDao.getAll();
    }

    @Override
    public void save(Venue venue) {
      venueDao.save(venue);
    }

    @Override
    public void update(Venue venue, String[] params) {
      venueDao.update(venue, params);
    }

    @Override
    public void delete(Venue venue) {
     venueDao.delete(venue);
    }
}
