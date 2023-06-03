package service;

import dao.ProductTypeDao;
import dao.ProductTypeDaoImpl;
import model.ProductType;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class ProductTypeService {
    ProductTypeDao ProductTypeDao;
    Map<String, ProductType> map;
    public ProductTypeService() throws FileNotFoundException {
        this.ProductTypeDao = new ProductTypeDaoImpl();
        this.map = ProductTypeDao.loadProductTypes();
    }

    public boolean ProductTypeExists(String key) {
        return map.containsKey(key);
    }

    public ProductType getProductType(String key) {
        return map.get(key);
    }

    public Set<String> getAllProductTypes() {
        return map.keySet();
    }
}
