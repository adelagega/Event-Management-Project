package eventmanagementproject.service;

import eventmanagementproject.dao.exception.DaoException;
import eventmanagementproject.dao.interfaces.SupplierDao;
import eventmanagementproject.entity.Booking;
import eventmanagementproject.entity.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class SupplierService {
    private final static Logger logger = LoggerFactory.getLogger(SupplierService.class);
    private final SupplierDao supplierDao;

    public SupplierService(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }


    public Supplier getSupplier(int id) {
        try {
            return supplierDao.get(id);
        } catch (DaoException e) {
            logger.error("Error retrieving supplier with id {}", id, e);
            throw e;
        }
    }

    public List<Supplier> getAllSuppliers() {
        try {
            return supplierDao.getAll();
        } catch (DaoException e) {
            logger.error("Error retrieving all suppliers", e);
            throw e;
        }
    }

    public void createSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        try {
            supplierDao.create(supplier);
        } catch (DaoException e) {
            logger.error("Error saving supplier", e);
            throw e;
        }
    }

    public void updateSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        try {
            supplierDao.update(supplier);
        } catch (DaoException e) {
            logger.error("Error updating supplier with id {}", supplier.getSupplierId(), e);
            throw e;
        }
    }

    public void deleteSupplier(int id) {
        try {
            supplierDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error deleting supplier with id {}", id, e);
            throw e;
        }
    }
}
