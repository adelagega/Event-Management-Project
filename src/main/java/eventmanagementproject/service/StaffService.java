package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.StaffDao;
import eventmanagementproject.entity.Booking;
import eventmanagementproject.entity.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StaffService {

    private final static Logger logger = LoggerFactory.getLogger(StaffService.class);
    private final StaffDao staffDao;

    public StaffService(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public Staff getStaff(int id) {
        try {
            return staffDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving staff with id {}", id, e);
            throw e;
        }
    }

    public List<Staff> getAllStaff() {
        try {
            return staffDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all staff members", e);
            throw e;
        }
    }

    public void createStaff(Staff staff) {
        if (staff == null) {
            throw new IllegalArgumentException("Staff cannot be null");
        }
        try {
            staffDao.create(staff);
        } catch (DaoException e) {
            logger.error("Error saving staff", e);
            throw e;
        }
    }

    public void updateStaff(Staff staff) {
        if (staff == null) {
            throw new IllegalArgumentException("Staff cannot be null");
        }
        try {
            staffDao.update(staff);
        } catch (DaoException e) {
            logger.error("Error updating staff with id {}", staff.getStaffId(), e);
            throw e;
        }
    }

    public void deleteStaff(int id) {
        try {
            staffDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting staff with id {}", id, e);
            throw e;
        }
    }
}

