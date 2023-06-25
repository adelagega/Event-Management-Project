package dao;

import entity.Booking;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDao extends AbstractDao<Booking>{
        @Override
        public Booking get(int id) {
            String SQL_SELECT = "Select * from bookings where booking_id=?";
            try (PreparedStatement preparedStatement = this.prepareStatement(SQL_SELECT)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    return new Booking(rs.getInt("booking_id"),rs.getInt("client_id"),
                            rs.getInt("event_id"),rs.getString("payment_status"));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to fetch booking with id",e);
            }
            return null;
        }

        @Override
    public List<Booking> getAll() {
        List<Booking> bookings= new ArrayList<>();
        String SQL_SELECT_ALL = "Select* from bookings";
        try(PreparedStatement ps=this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                bookings.add(new Booking(rs.getInt("booking_id"),rs.getInt("client_id"),
                        rs.getInt("event_id"), rs.getString("payment_status")));
            }
            return bookings;
        } catch(SQLException e){
            throw new RuntimeException("Failed to fetch all bookings",e);
        }
    }

    @Override
    public void save(Booking booking) {
        String SQL_INSERT = "Insert into bookings(client_id, event_id, payment_status) values(?,?,?)";
        try(PreparedStatement ps= this.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, booking.getClientId());
            ps.setInt(2, booking.getEventId());
            ps.setString(3,booking.getPaymentStatus());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert booking",e);
        }
    }

    @Override
    public void update(Booking booking, String[] params) {
        if (params == null || params.length != 3) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        String SQL_UPDATE="Update bookings set client_id=?,event_id=?, payment_status=? where booking_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_UPDATE)){
            ps.setInt(1, Integer.parseInt(params[0]));
            ps.setInt(2, Integer.parseInt(params[1]));
            ps.setString(3, params[2]);
            ps.setInt(4,booking.getBookingId());
            ps.executeUpdate();
        } catch(SQLException e){
           throw new RuntimeException("Failed to update booking",e);
        }
    }

    @Override
    public void delete(Booking booking) {
        String SQL_DELETE="Delete from bookings where booking_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
            ps.setInt(1,booking.getBookingId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to delete booking",e);
        }
    }
}

