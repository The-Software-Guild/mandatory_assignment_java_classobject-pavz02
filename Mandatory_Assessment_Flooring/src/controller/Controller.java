package controller;

import model.ProductType;
import model.Tax;
import service.OrderService;
import service.ProductTypeService;
import service.TaxService;
import view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class Controller {
    View view;
    OrderService orderService;
    TaxService taxService;
    ProductTypeService productTypeService;

    public Controller() {
        try {
            this.view = new View();
            this.orderService = new OrderService();
            this.productTypeService = new ProductTypeService();
            this.taxService = new TaxService();
        } catch (FileNotFoundException e) {
            view.printFileErrorMessage();
        }
    }

    public void start() {
        int option = view.printOptions();
        while (option != 6) {
            switch (option) {
                case 1 -> view.printOrders(orderService.getAllOrders());
                case 2 -> this.addOrder();
                case 3 -> this.editOrder();
                case 4 -> this.removeOrder();
                case 5 -> this.exportOrders();
                default -> view.printOptionErrorMessage();
            }
            option = view.printOptions();
        }
    }

    private void addOrder() {
        int orderNumber = view.getOrderNumber();
        if (orderService.orderExists(orderNumber)) {
            view.printOrderErrorMessage();
            return;
        }

        String tax = view.getTax(taxService.getAllTaxes());
        if (!taxService.taxExists(tax)) {
            view.printTaxErrorMessage();
            return;
        }
        Tax taxObject = taxService.getTax(tax);

        String productType = view.getProductType(productTypeService.getAllProductTypes());
        if (!productTypeService.ProductTypeExists(productType)) {
            view.printProductErrorMessage();
            return;
        }
        ProductType productTypeObject = productTypeService.getProductType(productType);

        String customerName = view.getCustomerName();
        BigDecimal area = new BigDecimal(view.getArea());

        try {
            orderService.addOrder(orderNumber, customerName, tax, taxObject.getTaxRate(), productType,
                    area, productTypeObject.getCostPerSquareFoot(), productTypeObject.getLaborCostPerSquareFoot());
        } catch (IOException e) {
            view.printFileErrorMessage();
        }
    }

    private void editOrder() {
        int orderNumber = view.getOrderNumber();
        if (!orderService.orderExists(orderNumber)) {
            view.printOrderErrorMessage();
            return;
        }

        int whatToEdit = view.editOptions();

        switch (whatToEdit) {
            case 1 -> {
                String customerName = view.getCustomerName();
                orderService.editOrderCustomerName(orderNumber, customerName);
            }
            case 2 -> {
                String tax = view.getTax(taxService.getAllTaxes());
                if (!taxService.taxExists(tax)) {
                    view.printTaxErrorMessage();
                    return;
                }
                Tax taxObject = taxService.getTax(tax);

                orderService.editOrderTax(orderNumber, tax, taxObject.getTaxRate());
            }
            case 3 -> {
                String productType = view.getProductType(productTypeService.getAllProductTypes());
                if (!productTypeService.ProductTypeExists(productType)) {
                    view.printProductErrorMessage();
                    return;
                }
                ProductType productTypeObject = productTypeService.getProductType(productType);

                orderService.editOrderProductType(orderNumber, productType,
                        productTypeObject.getCostPerSquareFoot(), productTypeObject.getLaborCostPerSquareFoot());
            }
            case 4 -> {
                BigDecimal area = new BigDecimal(view.getArea());
                orderService.editOrderArea(orderNumber, area);
            }
            default -> view.printOptionErrorMessage();
        }
    }

    private void removeOrder() {
        int orderNumber = view.getOrderNumber();
        if (!orderService.orderExists(orderNumber)) {
            view.printOrderErrorMessage();
            return;
        }

        orderService.removeOrder(orderNumber);
    }

    private void exportOrders() {
        try {
            orderService.exportOrders();
        } catch (IOException e) {
            view.printFileErrorMessage();
        }
    }
}
