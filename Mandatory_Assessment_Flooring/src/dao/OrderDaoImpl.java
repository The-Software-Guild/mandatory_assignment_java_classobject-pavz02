package dao;

import model.Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Map<String, List<Order>> loadOrders() throws FileNotFoundException {
        Map<String, List<Order>> map = new HashMap<>();
        File folder = new File(System.getProperty("user.dir") + "/Mandatory_Assessment_Flooring/storage/orders/");
        File[] files = folder.listFiles();

        for (File file : files) {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) scanner.nextLine();

            List<Order> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] components = scanner.nextLine().split(",");
                list.add(new Order(Integer.parseInt(components[0]), components[1], components[2],
                        new BigDecimal(components[3]), components[4], new BigDecimal(components[5]),
                        new BigDecimal(components[6]), new BigDecimal(components[7]), new BigDecimal(components[8]),
                        new BigDecimal(components[9]), new BigDecimal(components[10]), new BigDecimal(components[11])));
            }

            map.put(file.getName(), list);
        }

        return map;
    }

    @Override
    public void exportOrders(Map<String, List<Order>> map) throws IOException {
        for(File file: new File(System.getProperty("user.dir") + "/Mandatory_Assessment_Flooring/storage/orders/").listFiles())
            file.delete();

        for (String key : map.keySet()) {
            File file = new File(System.getProperty("user.dir") + "/Mandatory_Assessment_Flooring/storage/orders/" + key);
            FileWriter fw = new FileWriter(file, false);
            fw.write("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total\n");

            for (Order order : map.get(key)) {
                fw.write(order.getOrderNumber() + "," + order.getCustomerName() + "," + order.getState() + "," +
                        order.getTaxRate() + "," + order.getProductType() + "," + order.getArea() + "," +
                        order.getCostPerSquareFoot() + "," + order.getLaborCostPerSquareFoot() + "," +
                        order.getMaterialCost() + "," + order.getLaborCost() + "," + order.getTax() + "," +
                        order.getTotal() + "\n");
            }

            fw.close();
        }
    }

    @Override
    public String createNewOrder() throws IOException {
        String name = "Orders_" + DateTimeFormatter.ofPattern("DDMMYYYY").format(LocalDate.now() ) + ".txt";
        File file = new File(System.getProperty("user.dir") + "/Mandatory_Assessment_Flooring/storage/orders/" + name);

        if (!file.exists()) {
            file.createNewFile();
            FileWriter fw = new FileWriter(file, false);
            fw.write("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total\n");
        }

        return name;
    }
}
