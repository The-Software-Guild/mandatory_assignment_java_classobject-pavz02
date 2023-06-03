package view;

import model.Order;

import java.util.List;
import java.util.Set;

public class View {
    IO io;

    public View() {
        this.io = new IO();
    }

    public int printOptions() {
        io.print(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                "  * <<Flooring Program>>\n" +
                "  * 1. Display Orders\n" +
                "  * 2. Add an Order\n" +
                "  * 3. Edit an Order\n" +
                "  * 4. Remove an Order\n" +
                "  * 5. Export All Data\n" +
                "  * 6. Quit\n" +
                "  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        System.out.print("Enter your option here: ");
        return io.nextInt();
    }

    public int editOptions() {
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                "  * 1. Edit Customer name\n" +
                "  * 2. Edit tax\n" +
                "  * 3. Edit product type\n" +
                "  * 4. Edit area\n" +
                "  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        System.out.print("Enter your option here: ");
        return io.nextInt();
    }

    public void printFileErrorMessage() {
        io.print("Error: The files you required for this application are not found !!!");
    }

    public void printOrderErrorMessage() {
        io.print("Error: The order number is invalid !!!");
    }

    public void printTaxErrorMessage() {
        io.print("Error: The tax type is invalid !!!");
    }

    public void printProductErrorMessage() {
        io.print("Error: The product type is invalid !!!");
    }

    public void printOptionErrorMessage() {
        io.print("Error: The option selected is invalid !!!");
    }

    public void printOrders(List<Order> orders)  {
        for (Order o : orders)
            io.printOrder(o);
    }

    public int getOrderNumber() {
        io.print("Enter the order number: ");
        return io.nextInt();
    }

    public String getTax(Set<String> taxTypes) {
        io.print("Here are the tax types: ");
        for (String taxType : taxTypes) io.print(taxType);
        io.print("Enter the tax type you want: ");
        return io.nextString();
    }

    public String getProductType(Set<String> productTypes) {
        io.print("Here are the product types: ");
        for (String productType : productTypes) io.print(productType);
        io.print("Enter the product type you want: ");
        return io.nextString();
    }

    public String getCustomerName() {
        io.print("Enter the customer's name: ");
        return io.nextString();
    }

    public String getArea() {
        io.print("Enter the area: ");
        return io.nextString();
    }
}
