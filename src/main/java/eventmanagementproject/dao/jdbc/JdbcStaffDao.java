package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.StaffDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcStaffDao extends AbstractDao<Staff> implements StaffDao {
    private final static Logger logger = LoggerFactory.getLogger(JdbcStaffDao.class);

    @Override
    public Staff get(int id) {
        try (PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM staff WHERE staff_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Staff(rs.getInt("staff_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("role"));
            } else {
                throw new DaoException("Staff with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching staff with id{}", id, e);
            throw new DaoException("Failed to fetch staff with id" + id, e);
        }
    }

    @Override
    public List<Staff> getAll() {
        List<Staff> staffList = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM staff")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                staffList.add(new Staff(rs.getInt("staff_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("role")));
            }
            return staffList;
        } catch (SQLException e) {
            logger.error("Error fetching all staff", e);
            throw new DaoException("Failed to fetch all staff", e);
        }
    }

    @Override
    public void create(Staff staff) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO staff (first_name,last_name,role) VALUES (?,?,?)")) {
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting staff", e);
            throw new DaoException("Failed to insert staff", e);
        }
    }

    @Override
    public void update(Staff staff) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE staff SET first_name=?, last_name=?, role=? WHERE staff_id=?")) {
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getRole());
            ps.setInt(4, staff.getStaffId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating staff with id {}", staff.getStaffId(), e);
            throw new DaoException("Failed to update staff", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM staff WHERE staff_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting staff with id {}", id, e);
            throw new DaoException("Failed to delete staff with id " + id, e);
        }
    }
}
