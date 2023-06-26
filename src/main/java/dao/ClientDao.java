package dao;

import entity.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends AbstractDao<Client> {
    @Override
    public Client get(int id) {
        String SQL_SELECT = "Select * from clients where client_id=?";
        try (PreparedStatement preparedStatement = this.prepareStatement(SQL_SELECT)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("client_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"),
                        rs.getString("phone_number"));
            }
        } catch (SQLException e) {
          throw new RuntimeException("Failed to fetch client with id: "+id, e);
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
       List<Client> clients= new ArrayList<>();
       String SQL_SELECT_ALL = "Select* from clients";
       try(PreparedStatement ps=this.prepareStatement(SQL_SELECT_ALL)) {
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               clients.add(new Client(rs.getInt("client_id"),rs.getString("first_name"),
                       rs.getString("last_name"), rs.getString("email"), rs.getString("phone_number")
                       ));
           }
         return clients;
       } catch(SQLException e){
           throw new RuntimeException("Failed to fetch all clients", e);
       }
    }

    @Override
    public void save(Client client) {
        String SQL_INSERT = "Insert into clients(first_name,last_name,email,phone_number) values(?,?,?,?)";
        try(PreparedStatement ps= this.prepareStatement(SQL_INSERT)) {
        ps.setString(1, client.getFirstName());
        ps.setString(2, client.getLastName());
        ps.setString(3,client.getEmail());
        ps.setString(4, client.getPhoneNumber());
        ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to insert client",e);
        }
    }

    @Override
    public void update(Client client, String[] params) {
     if (params == null || params.length != 4) {
            throw new IllegalArgumentException("Invalid input parameters");
      }
     String SQL_UPDATE="Update clients set first_name=?,last_name=?,email=?, phone_number=? where client_id=?";
     try(PreparedStatement ps=this.prepareStatement(SQL_UPDATE)){
         ps.setString(1, params[0]);
         ps.setString(2, params[1]);
         ps.setString(3, params[2]);
         ps.setString(4, params[3]);
         ps.setInt(5,client.getClientId());
         ps.executeUpdate();
     } catch(SQLException e){
         throw new RuntimeException("Failed to update client",e);
     }
    }

    @Override
    public void delete(Client client) {
        String SQL_DELETE="Delete from clients where client_id=?";
        try(PreparedStatement ps=this.prepareStatement(SQL_DELETE)){
        ps.setInt(1, client.getClientId());
        ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Failed to delete client", e);
        }
    }
}
