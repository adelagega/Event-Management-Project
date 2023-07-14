package eventmanagementproject.dao.jdbc;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.ClientDao;
import eventmanagementproject.dao.util.AbstractDao;
import eventmanagementproject.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcClientDao extends AbstractDao<Client> implements ClientDao {

    private final static Logger logger = LoggerFactory.getLogger(JdbcClientDao.class);

    @Override
    public Client get(int id) {
        try (PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM clients WHERE client_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("client_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"),
                        rs.getString("phone_number"));
            } else {
                throw new DaoException("Client with id " + id + " not found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching client with id{}", id, e);
            throw new DaoException("Failed to fetch client with id" + id, e);
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement ps = this.prepareStatement("SELECT * FROM clients")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getInt("client_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"), rs.getString("phone_number")));
            }
            return clients;
        } catch (SQLException e) {
            logger.error("Error fetching all clients", e);
            throw new DaoException("Failed to fetch all clients", e);
        }
    }

    @Override
    public void create(Client client) {
        try (PreparedStatement ps = this.prepareStatement("INSERT INTO clients (first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting client", e);
            throw new DaoException("Failed to insert client", e);
        }
    }

    @Override
    public void update(Client client) {
        try (PreparedStatement ps = this.prepareStatement("UPDATE clients SET first_name=?, last_name=?, email=?, phone_number=? WHERE client_id=?")) {
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhoneNumber());
            ps.setInt(5, client.getClientId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating client with id {}", client.getClientId(), e);
            throw new DaoException("Failed to update client", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = this.prepareStatement("DELETE FROM clients WHERE client_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting client with id {}", id, e);
            throw new DaoException("Failed to delete client with id " + id, e);
        }
    }
}
