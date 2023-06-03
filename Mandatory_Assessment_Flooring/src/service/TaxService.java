package service;

import dao.TaxDao;
import dao.TaxDaoImpl;
import model.Tax;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class TaxService {
    TaxDao taxDao;
    Map<String, Tax> map;
    public TaxService() throws FileNotFoundException {
        this.taxDao = new TaxDaoImpl();
        this.map = taxDao.loadTaxes();
    }

    public boolean taxExists(String key) {
        return map.containsKey(key);
    }

    public Tax getTax(String key) {
        return map.get(key);
    }

    public Set<String> getAllTaxes() {
        return map.keySet();
    }
}
