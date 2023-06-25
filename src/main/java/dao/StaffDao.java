package dao;

import entity.Client;
import entity.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao extends AbstractDao<Staff>{
    @Override
    public Staff get(int id) {
        String SQL_SELECT = "Select * from staff where staff_id=?";
        try (PreparedStatement preparedStatement = this.prepareStatement(SQL_SELECT)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Staff(rs.getInt("staff_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch staff with staff id:"+ id, e);
        }
        return null;
    }

    @Override
    public List<Staff> getAll() {
        List<Staff> staffList= new ArrayList<>();
        String SQL_SELECT_ALL = "Select* from staff";
        try(PreparedStatement ps=this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                staffList.add(new Staff(rs.getInt("staff_id"),rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("role")));
            }
            return staffList;
        } catch(SQLException e){
            throw new RuntimeException("Failed to fetch all staff",e);
        }
    }

    @Override
    public void save(Staff staff) {
        String SQL_INSERT = "Insert into staff(first_name,last_name,role) values(?,?,?)";
        try(PreparedStatement ps= this.prepareStatement(SQL_INSERT)) {
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getRole());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert staff", e);
        }
    }

    @Override
    public void update(Staff staff, String[] params) {
        if (params == null || params.length != 3) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        String SQL_UPDATE="Update staff set first_name=?,last_name=?,role=? where staff_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_UPDATE)){
            ps.setString(1, params[0]);
            ps.setString(2, params[1]);
            ps.setString(3, params[2]);
            ps.setInt(4,staff.getStaffId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to update staff", e);
        }
    }

    @Override
    public void delete(Staff staff) {
        String SQL_DELETE="Delete from staff where staff_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
            ps.setInt(1, staff.getStaffId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to delete staff",e);
        }
    }
}
