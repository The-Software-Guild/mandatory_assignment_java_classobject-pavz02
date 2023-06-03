package dao;

import model.Tax;

import java.io.FileNotFoundException;
import java.util.Map;

public interface TaxDao {
    Map<String, Tax> loadTaxes() throws FileNotFoundException;
}
