package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.ClientDao;
import eventmanagementproject.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientService {
    private final static Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Client getClient(int id) {
        try {
            return clientDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving client with id {}", id, e);
            throw e;
        }
    }

    public List<Client> getAllClients() {
        try {
            return clientDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all clients", e);
            throw e;
        }
    }

    public void createClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        try {
            clientDao.create(client);
        } catch (DaoException e) {
            logger.error("Error saving client", e);
            throw e;
        }
    }

    public void updateClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        try {
            clientDao.update(client);
        } catch (DaoException e) {
            logger.error("Error updating client with id {}", client.getClientId(), e);
            throw e;
        }
    }

    public void deleteClient(int id) {
        try {
            clientDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting client with id {}", id, e);
            throw e;
        }
    }
}
