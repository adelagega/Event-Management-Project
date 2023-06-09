package dao;

import entity.Invoice;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDao extends AbstractDao<Invoice>{
    @Override
    public Invoice get(int id) {
        String SQL_SELECT = "SELECT* FROM invoices where invoice_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_SELECT)) {
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new Invoice(rs.getInt("invoice_id"), rs.getInt("booking_id"),
                        rs.getDate("invoice_date"), rs.getFloat("total_amount"));
            }
        } catch(SQLException e){
           throw new RuntimeException("Failed to fetch invoice with id"+id, e);
        }
        return null;
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();
        String SQl_Select_ALL = "SELECT * FROM INVOICES";
        try(PreparedStatement ps = this.prepareStatement(SQl_Select_ALL)) {
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               invoices.add(new Invoice(rs.getInt("invoice_id"), rs.getInt("booking_id")
               ,rs.getDate("invoice_date"), rs.getFloat("total_amount")));
           }
           return invoices;
        } catch (SQLException e){
            throw new RuntimeException("Failed to fetch all invoices");
        }
    }

    @Override
    public void save(Invoice invoice) {
        String SQl_INSERT = "Insert into invoices(booking_id,invoice_date,total_amount) values(?,?,?)";
        try(PreparedStatement ps = this.prepareStatement(SQl_INSERT)){
            ps.setInt(1,invoice.getBookingid());
            ps.setDate(2, (Date) invoice.getInvoiceDate());
            ps.setFloat(3,invoice.getTotalAmount());
            ps.executeUpdate();
        } catch(SQLException e){
          throw new RuntimeException("Failed to insert invoice", e);
        }
    }

    @Override
    public void update(Invoice invoice, String[] params) {
        if (params == null || params.length != 3) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
      String SQL_UPDATE = "Update invoices set booking_id=?, invoice_date=?, total_amount=? where invoice_id=?";
      try(PreparedStatement ps = this.prepareStatement(SQL_UPDATE)){
           ps.setInt(1, Integer.parseInt(params[0]));
           ps.setDate(2, Date.valueOf(params[1]));
           ps.setFloat(3, Float.parseFloat(params[2]));
           ps.setInt(4,invoice.getInvoiceId());
           ps.executeUpdate();
      } catch(SQLException e){
         throw new RuntimeException("Failed to update invoice", e);
      }
    }

    @Override
    public void delete(Invoice invoice) {
     String SQL_DELETE = "Delete from invoices where invoice_id=?";
     try(PreparedStatement ps = this.prepareStatement(SQL_DELETE)){
         ps.setInt(1,invoice.getInvoiceId());
         ps.executeUpdate();
     } catch(SQLException e){
        throw new RuntimeException("Failed to delete invoice",e);
     }
    }
}
