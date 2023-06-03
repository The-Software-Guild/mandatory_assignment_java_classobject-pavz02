package dao;

import model.Tax;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class TaxDaoImpl implements TaxDao {

    @Override
    public Map<String, Tax> loadTaxes() throws FileNotFoundException {
        Map<String, Tax> map = new HashMap<>();

        File file = new File(System.getProperty("user.dir") + "/Mandatory_Assessment_Flooring/storage/Taxes.txt");
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] components = scanner.nextLine().split(",");
            map.put(components[0], new Tax(components[0], components[1], new BigDecimal(components[2])));
        }

        return map;
    }
}
