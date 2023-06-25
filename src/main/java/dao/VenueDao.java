package dao;

import entity.Venue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueDao extends AbstractDao<Venue>{
    @Override
    public Venue get(int id) {
        String SQL_SELECT = "Select * from venues where venue_id=?";
        try (PreparedStatement preparedStatement = this.prepareStatement(SQL_SELECT)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Venue(rs.getInt("venue_id"), rs.getString("venue_name"),
                        rs.getInt("capacity"), rs.getString("location"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch venue with id: "+id, e);
        }
        return null;
    }

    @Override
    public List<Venue> getAll() {
        List<Venue> venues = new ArrayList<>();
        String SQL_SELECT_ALL = "Select * from venues";
        try (PreparedStatement ps = this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                venues.add(new Venue(rs.getInt("venue_id"), rs.getString("venue_name")
                        , rs.getInt("capacity"), rs.getString("location")));
            } return venues;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all venues");
        }
    }


    @Override
    public void save(Venue venue) {
        String SQL_INSERT="Insert into venues(venue_name, capacity, location) values(?,?,?)";
        try(PreparedStatement ps=this.prepareStatement(SQL_INSERT)){
            ps.setString(1, venue.getVenueName());
            ps.setInt(2,venue.getCapacity());
            ps.setString(3, venue.getLocation());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert venue", e);
        }
    }

    @Override
    public void update(Venue venue, String[] params) {
        if (params == null || params.length != 3) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
     String SQL_UPDATE="update venues set venue_name=?, capacity=?, location=? where venue_id=?";
     try(PreparedStatement ps = this.prepareStatement(SQL_UPDATE)) {
         ps.setString(1,params[0]);
         ps.setInt(2,Integer.parseInt(params[1]));
         ps.setString(3,params[2]);
         ps.setInt(4, venue.getVenueId());
         ps.executeUpdate();
        } catch(SQLException e) {
          throw new RuntimeException("Failed to update venue", e);
        }
    }

    @Override
    public void delete(Venue venue) {
     String SQL_DELETE="Delete from venues where venue_id=?";
     try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
         ps.setInt(1, venue.getVenueId());
         ps.executeUpdate();
     } catch(SQLException e){
         throw new RuntimeException("Failed to delete venue", e);
     }
    }
}
