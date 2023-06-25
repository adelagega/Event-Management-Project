package dao;

import entity.EventSupplierAssociation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventSupplierAssociationDao extends AbstractDao<EventSupplierAssociation>{
    @Override
    public EventSupplierAssociation get(int id) {
        try {
            PreparedStatement ps = this.prepareStatement("SELECT * FROM event_suppliers WHERE supplier_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EventSupplierAssociation(rs.getInt("event_id"), rs.getInt("supplier_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch EventSupplierAssociation with event_id: " + id, e);
        }
        return null;
    }

    @Override
    public List<EventSupplierAssociation> getAll() {
        List<EventSupplierAssociation> eventSupplierAssociationList = new ArrayList<>();
        String SQL_SELECT_ALL = "Select * from event_suppliers";
        try(PreparedStatement ps=this.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                eventSupplierAssociationList.add(new EventSupplierAssociation(rs.getInt("event_id"),
                        rs.getInt("supplier_id")));
            }
            return eventSupplierAssociationList;
        } catch(SQLException e){
           throw new RuntimeException("Failed to fetch all EventStaffAssociation");
        }
    }

    @Override
    public void save(EventSupplierAssociation eventSupplierAssociation) {
        String SQL_INSERT = "Insert into event_suppliers(event_id, supplier_id) values(?,?)";
        try(PreparedStatement ps= this.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, eventSupplierAssociation.getEventId());
            ps.setInt(2, eventSupplierAssociation.getSupplierId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert eventSupplierAssociation", e);
        }
    }

    @Override
    public void update(EventSupplierAssociation eventSupplierAssociation, String[] params) {
    }

    @Override
    public void delete(EventSupplierAssociation eventSupplierAssociation) {
        String SQL_DELETE="Delete from event_suppliers where event_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
            ps.setInt(1, eventSupplierAssociation.getEventId());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to delete eventStaffAssociation",e);
        }
    }
}
