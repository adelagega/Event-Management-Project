package service;

import dao.GenericDao;
import entity.Booking;

import java.util.List;

public class BookingService implements GenericService<Booking>{

    private final GenericDao<Booking> bookingDao;

    public BookingService(GenericDao<Booking> bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public Booking get(int id) {
        return bookingDao.get(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingDao.getAll();
    }

    @Override
    public void save(Booking booking) {
       bookingDao.save(booking);
    }

    @Override
    public void update(Booking booking, String[] params) {
        bookingDao.update(booking,params);
    }

    @Override
    public void delete(Booking booking) {
      bookingDao.delete(booking);
    }
}
