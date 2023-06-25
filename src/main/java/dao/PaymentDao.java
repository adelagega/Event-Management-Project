package dao;

import entity.Payment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao extends AbstractDao<Payment>{
    @Override
    public Payment get(int id) {
        String SQL_SELECT = "Select * from payments where payment_id=?";
        try(PreparedStatement ps= this.prepareStatement(SQL_SELECT)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Payment(rs.getInt("payment_id"),rs.getInt("booking_id"), rs.getFloat("amount"),
                        rs.getDate("payment_date"), rs.getString("payment_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch payment with id"+id, e);
        }
        return null;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        String SQL_SELECT_ALL = "Select * from payments";
        try(PreparedStatement ps = this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                payments.add(new Payment(rs.getInt("payment_id"), rs.getInt("booking_id"),
                        rs.getFloat("amount"), rs.getDate("payment_date"), rs.getString("payment_type")));
            }
           return payments;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all payments");
        }
    }

    @Override
    public void save(Payment payment) {
        String SQL_INSERT = "Insert into payments(booking_id,amount,payment_date,payment_type) values(?,?,?,?)";
        try(PreparedStatement ps= this.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, payment.getBookingId());
            ps.setFloat(2, payment.getAmount());
            ps.setDate(3, (Date) payment.getPaymentDate());
            ps.setString(4, payment.getPaymentType());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert payment",e);
        }
    }

    @Override
    public void update(Payment payment, String[] params) {
        if (params == null || params.length != 4) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        String SQL_UPDATE="Update payments set booking_id=?,amount=?,payment_date=?,payment_type where payment_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_UPDATE)){
            ps.setInt(1, Integer.parseInt(params[0]));
            ps.setFloat(2, Float.parseFloat(params[1]));
            ps.setDate(3, Date.valueOf(params[2]));
            ps.setString(4, params[3]);
            ps.setInt(5,payment.getPaymentId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to update payment");
        }
    }

    @Override
    public void delete(Payment payment) {
        String SQL_DELETE="Delete from payments where payment_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
            ps.setInt(1, payment.getPaymentId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to delete payment",e);
        }
    }
}
