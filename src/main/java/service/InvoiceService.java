package service;

import dao.GenericDao;
import entity.Invoice;

import java.util.List;

public class InvoiceService implements GenericService<Invoice>{

    private final GenericDao<Invoice> invoiceDao;

    public InvoiceService(GenericDao<Invoice> invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @Override
    public Invoice get(int id) {
        return invoiceDao.get(id);
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceDao.getAll();
    }

    @Override
    public void save(Invoice invoice) {
      invoiceDao.save(invoice);
    }

    @Override
    public void update(Invoice invoice, String[] params) {
     invoiceDao.update(invoice,params);
    }

    @Override
    public void delete(Invoice invoice) {
     invoiceDao.delete(invoice);
    }
}
