package dao;


import entity.EventStaffAssociation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventStaffAssociationDao extends AbstractDao<EventStaffAssociation>{


    @Override
    public EventStaffAssociation get(int id) {
        try {
            PreparedStatement ps = this.prepareStatement("SELECT * FROM event_staff WHERE event_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EventStaffAssociation(rs.getInt("event_id"), rs.getInt("staff_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch EventStaffAssociation with event_id: " + id, e);
        }
        return null;
    }

    @Override
    public List<EventStaffAssociation> getAll() {
        List<EventStaffAssociation> eventStaffAssociationList = new ArrayList<>();
        String SQL_SELECT_ALL = "Select* from event_staff";
        try(PreparedStatement ps=this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
              eventStaffAssociationList.add(new EventStaffAssociation(rs.getInt("event_id"),
                      rs.getInt("staff_id")));
            }
            return eventStaffAssociationList;
        } catch(SQLException e){
            throw new RuntimeException("Failed to fetch all EventStaffAssociations", e);
        }
    }

    @Override
    public void save(EventStaffAssociation eventStaffAssociation) {
        String SQL_INSERT = "Insert into event_staff(event_id, staff_id) values(?,?)";
        try(PreparedStatement ps= this.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, eventStaffAssociation.getEventId());
            ps.setInt(2, eventStaffAssociation.getStaffId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert eventStaffAssociation", e);
        }
    }

    @Override
    public void update(EventStaffAssociation eventStaffAssociation, String[] params) {
    }

    @Override
    public void delete(EventStaffAssociation eventStaffAssociation) {
        String SQL_DELETE="Delete from event_staff where event_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
            ps.setInt(1, eventStaffAssociation.getEventId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to delete eventStaffAssociation", e);
        }
    }
}
