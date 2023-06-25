package dao;

import entity.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao extends AbstractDao<Supplier>{
    @Override
    public Supplier get(int id) {
     String SQL_SELECT="Select*from suppliers where supplier_id=?";
     try(PreparedStatement ps=this.prepareStatement(SQL_SELECT)){
         ResultSet rs=ps.executeQuery(SQL_SELECT);
         if(rs.next()){
             return new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name")
                     ,rs.getString("supplier_type"), rs.getString("contact_name"),
                     rs.getString("contact_email"),rs.getString("contact_phone"));
         }
     } catch (SQLException e){
         throw new RuntimeException("Failed to fetch supplier with id: "+id, e);
     }
     return null;
    }

    @Override
    public List<Supplier> getAll() {
      List<Supplier> suppliers= new ArrayList<>();
      String SQL_SELECT_ALL="Select * from suppliers";
      try(PreparedStatement ps=this.prepareStatement(SQL_SELECT_ALL);
          ResultSet rs=ps.executeQuery(SQL_SELECT_ALL)){
          while(rs.next()){
             suppliers.add(new Supplier(rs.getInt("supplier_id"),rs.getString("supplier_name"),
                     rs.getString("supplier_type"), rs.getString("contact_name"),
                     rs.getString("contact_email"),rs.getString("contact_phone")));
          }
         return suppliers;
      } catch(SQLException e){
          throw new RuntimeException("Failed to fetch all supplier");
      }
    }

    @Override
    public void save(Supplier supplier) {
        String SQL_INSERT = "Insert into suppliers(supplier_name,supplier_type,contact_name, contact_email,contact_phone) values(?,?,?,?,?)";
        try(PreparedStatement ps= this.prepareStatement(SQL_INSERT)) {
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getSupplierType());
            ps.setString(3, supplier.getContactName());
            ps.setString(4, supplier.getContactEmail());
            ps.setString(5, supplier.getContactPhone());
            ps.executeUpdate();
        } catch(SQLException e){
           throw new RuntimeException("Failed to insert supplier", e);
        }
    }

    @Override
    public void update(Supplier supplier, String[] params) {
        if (params == null || params.length != 5) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        String SQL_UPDATE="Update suppliers set supplier_name=?,supplier_type=?,vcontact_name, contact_email=?, contact_phone=? where supplier_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_UPDATE)){
            ps.setString(1, params[0]);
            ps.setString(2, params[1]);
            ps.setString(3, params[2]);
            ps.setString(4, params[3]);
            ps.setString(5, params[4]);
            ps.setInt(6, supplier.getSupplierId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to update supplier", e);
        }
    }

    @Override
    public void delete(Supplier supplier) {
        String SQL_DELETE="Delete from vendors where supplier_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
            ps.setInt(1, supplier.getSupplierId());
            ps.executeUpdate();
        } catch(SQLException e){
           throw new RuntimeException("Failed to delete supplier");
        }
    }
}

