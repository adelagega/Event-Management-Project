package dao;


import entity.EventType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventTypeDao extends AbstractDao<EventType>{
    @Override
    public EventType get(int id) {
        String SQL_SELECT = "Select * from event_types where event_type_id=?";
        try (PreparedStatement preparedStatement = this.prepareStatement(SQL_SELECT)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new EventType(rs.getInt("event_type_"),
                        rs.getString("event_type_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch eventType with id" + id,e);
        }
        return null;
    }

    @Override
    public List<EventType> getAll() {
        List<EventType> eventTypes = new ArrayList<>();
        String SQL_SELECT_ALL = "Select * from event_types";
        try(PreparedStatement ps=this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                eventTypes.add(new EventType(rs.getInt("event_type_id"),
                        rs.getString("event_type_name")));
            }
            return eventTypes;
        } catch(SQLException e){
            throw new RuntimeException("Failed to fetch all eventTypes", e);
        }
    }

    @Override
    public void save(EventType eventType) {
        String SQL_INSERT = "Insert into event_types(event_type_name) values(?)";
        try(PreparedStatement ps = this.prepareStatement(SQL_INSERT)) {
            ps.setString(1, eventType.getEventTypeName());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert eventType", e);
        }
    }

    @Override
    public void update(EventType eventType, String[] params) {
        if (params == null || params.length != 1) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        String SQL_UPDATE="Update event_types set event_type_name=? where event_type_id=?";
        try(PreparedStatement ps = this.prepareStatement(SQL_UPDATE)){
            ps.setString(1, params[0]);
            ps.setInt(2,eventType.getEventTypeId());
            ps.executeUpdate();
        } catch(SQLException e){
           throw new RuntimeException("Failed to update eventType", e);
        }
    }

    @Override
    public void delete(EventType eventType) {
        String SQL_DELETE="Delete from event_types where event_type_id=?";
        try(PreparedStatement ps = this.prepareStatement(SQL_DELETE)){
            ps.setInt(1, eventType.getEventTypeId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to delete eventType",e);
        }
    }
}

