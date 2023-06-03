package dao;

import model.ProductType;

import java.io.FileNotFoundException;
import java.util.Map;

public interface ProductTypeDao {
    Map<String, ProductType> loadProductTypes() throws FileNotFoundException;
}
