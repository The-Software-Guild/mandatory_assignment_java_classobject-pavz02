package dao;

import model.ProductType;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class ProductTypeDaoImpl implements ProductTypeDao {
    @Override
    public Map<String, ProductType> loadProductTypes() throws FileNotFoundException {
        Map<String, ProductType> map = new HashMap<>();

        File file = new File(System.getProperty("user.dir") + "/Mandatory_Assessment_Flooring/storage/Products.txt");
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] components = scanner.nextLine().split(",");
            map.put(components[0],
                    new ProductType(components[0], new BigDecimal(components[1]), new BigDecimal(components[2])));
        }

        return map;
    }
}
