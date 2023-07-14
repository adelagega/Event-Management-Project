package eventmanagementproject.dao.interfaces;

import eventmanagementproject.entity.Client;
import java.util.List;

public interface ClientDao {
    void create(Client client);
    Client get(int id);
    List<Client> getAll();
    void update(Client client);
    void delete(int id);
}
