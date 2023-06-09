package service;

import dao.GenericDao;
import entity.Supplier;

import java.util.List;

public class SupplierService implements GenericService<Supplier>{

    private final GenericDao<Supplier> supplierDao;


    public SupplierService(GenericDao<Supplier> supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public Supplier get(int id) {
        return supplierDao.get(id);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierDao.getAll();
    }

    @Override
    public void save(Supplier supplier) {
       supplierDao.save(supplier);
    }

    @Override
    public void update(Supplier supplier, String[] params) {
      supplierDao.update(supplier,params);
    }

    @Override
    public void delete(Supplier supplier) {
      supplierDao.delete(supplier);
    }
}
