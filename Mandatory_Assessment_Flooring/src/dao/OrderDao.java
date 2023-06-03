package dao;

import model.Order;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    Map<String, List<Order>> loadOrders() throws FileNotFoundException;
    String createNewOrder() throws IOException;
    void exportOrders(Map<String, List<Order>> map) throws IOException;
}
