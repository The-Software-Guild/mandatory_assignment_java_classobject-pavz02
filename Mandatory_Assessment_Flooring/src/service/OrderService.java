package service;

import dao.*;
import model.Order;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class OrderService {
    OrderDao orderDao;
    Map<String, List<Order>> orders;
    public OrderService() throws FileNotFoundException {
        this.orderDao = new OrderDaoImpl();
        this.orders = orderDao.loadOrders();
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();

        for (String key : orders.keySet()) {
            list.addAll(orders.get(key));
        }

        return list;
    }

    public void addOrder(Integer orderNumber, String customerName, String state, BigDecimal taxRate, String productType,
                         BigDecimal area, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot) throws IOException {

        String order = orderDao.createNewOrder();

        BigDecimal materialCost = this.calculateMaterialCost(area, costPerSquareFoot);
        BigDecimal laborCost = this.calculateLaborCost(area, laborCostPerSquareFoot);
        BigDecimal tax = this.calculateTaxCost(materialCost, laborCost, taxRate);
        BigDecimal total = this.calculateTotal(materialCost, laborCost, tax);

        if (!this.orders.containsKey(order)) {
            this.orders.put(order, new ArrayList<Order>());
        }

        this.orders.get(order).add(new Order(orderNumber, customerName, state, taxRate, productType, area,
                costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total));
    }

    public boolean orderExists(Integer orderNumber) {
        int count = 0;

        for (String key : orders.keySet()) {
            count += orders.get(key).stream().filter(order -> Objects.equals(order.getOrderNumber(), orderNumber)).count();
        }

        return count == 1;
    }

    private Optional<Order> getOrder(Integer orderNumber) {
        Optional<Order> order = Optional.empty();

        for (String key : orders.keySet()) {
            if (order.isEmpty()) {
                order = orders.get(key).stream().filter(o -> Objects.equals(o.getOrderNumber(), orderNumber)).findFirst();
            }
        }

        return order;
    }

    public void editOrderCustomerName(Integer orderNumber, String customerName) {
        getOrder(orderNumber).ifPresent(o -> o.setCustomerName(customerName));
    }

    public void editOrderTax(Integer orderNumber, String state, BigDecimal taxRate) {
        Order o = getOrder(orderNumber).get();
        o.setState(state);
        o.setTaxRate(taxRate);

        o.setTax(this.calculateTaxCost(o.getMaterialCost(), o.getLaborCost(), o.getTaxRate()));
        o.setTotal(this.calculateTotal(o.getMaterialCost(), o.getLaborCost(), o.getTax()));
    }

    public void editOrderProductType(Integer orderNumber, String productType, BigDecimal cost, BigDecimal laborCost) {
        Order o = getOrder(orderNumber).get();
        o.setProductType(productType);
        o.setCostPerSquareFoot(cost);
        o.setLaborCostPerSquareFoot(laborCost);

        o.setMaterialCost(this.calculateMaterialCost(o.getArea(), o.getCostPerSquareFoot()));
        o.setLaborCost(this.calculateLaborCost(o.getArea(), o.getLaborCostPerSquareFoot()));
        o.setTax(this.calculateTaxCost(o.getMaterialCost(), o.getLaborCost(), o.getTaxRate()));
        o.setTotal(this.calculateTotal(o.getMaterialCost(), o.getLaborCost(), o.getTax()));
    }

    public void editOrderArea(Integer orderNumber, BigDecimal area) {
        Order o = getOrder(orderNumber).get();
        o.setArea(area);

        o.setMaterialCost(this.calculateMaterialCost(o.getArea(), o.getCostPerSquareFoot()));
        o.setLaborCost(this.calculateLaborCost(o.getArea(), o.getLaborCostPerSquareFoot()));
        o.setTax(this.calculateTaxCost(o.getMaterialCost(), o.getLaborCost(), o.getTaxRate()));
        o.setTotal(this.calculateTotal(o.getMaterialCost(), o.getLaborCost(), o.getTax()));
    }

    public void removeOrder(Integer orderNumber) {
        Optional<Order> order = Optional.empty();
        String orderKey = "";

        for (String key : orders.keySet()) {
            if (order.isEmpty()) {
                order = orders.get(key).stream().filter(o -> Objects.equals(o.getOrderNumber(), orderNumber)).findFirst();
                orderKey = key;
            }
        }

        if (order.isPresent()) orders.get(orderKey).remove(order.get());
    }

    public void exportOrders() throws IOException {
        orderDao.exportOrders(orders);
    }

    private BigDecimal calculateMaterialCost(BigDecimal area, BigDecimal costPerSquareFoot) {
        return area.multiply(costPerSquareFoot);
    }

    private BigDecimal calculateLaborCost(BigDecimal area, BigDecimal laborCostPerSquareFoot) {
        return area.multiply(laborCostPerSquareFoot);
    }

    private BigDecimal calculateTaxCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxRate) {
        return (materialCost.add(laborCost)).multiply(taxRate).movePointLeft(2);
    }

    private BigDecimal calculateTotal(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) {
        return materialCost.add(laborCost).add(tax);
    }
}
