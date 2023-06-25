package service;
import dao.GenericDao;
import entity.Client;
import java.util.List;

public class ClientService implements GenericService<Client> {

    private final GenericDao<Client> clientDao;

    public ClientService(GenericDao<Client> clientDao){
      this.clientDao=clientDao;
    }

    @Override
    public Client get(int id) {
        return clientDao.get(id);
    }

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    public void save(Client client) {
      clientDao.save(client);
    }

    @Override
    public void update(Client client, String[] params) {
      clientDao.update(client,params);
    }

    @Override
    public void delete(Client client) {
      clientDao.delete(client);
    }
}
