package service;

import dao.GenericDao;
import entity.Staff;

import java.util.List;

public class StaffService implements GenericService<Staff>{

    private final GenericDao<Staff> staffDao;

    public StaffService(GenericDao<Staff> staffDao) {
        this.staffDao = staffDao;
    }

    @Override
    public Staff get(int id) {
        return staffDao.get(id);
    }

    @Override
    public List<Staff> getAll() {
        return staffDao.getAll();
    }

    @Override
    public void save(Staff staff) {
      staffDao.save(staff);
    }

    @Override
    public void update(Staff staff, String[] params) {
     staffDao.update(staff, params);
    }

    @Override
    public void delete(Staff staff) {
      staffDao.delete(staff);
    }
}
