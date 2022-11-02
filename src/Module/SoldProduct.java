package Module;

import java.io.Serializable;
import java.time.LocalDate;

public class SoldProduct implements Serializable {
    String name;
    int productID;
    int billNumber;
    int quantity;
    double price;
    LocalDate soldDate;
    double totalPrice;

    double soldOverAPeriod;
    double priceOverAPeriod;
    static int idGenerator = 0;


    public SoldProduct()
    {
        productID = ++idGenerator;
        soldDate = LocalDate.now();
        soldOverAPeriod = 0;
        priceOverAPeriod = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public int getBillNumber() { return billNumber; }
    public void setBillNumber(int billNumber) { this.billNumber = billNumber; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDate getDate() { return soldDate; }

    public double getTotalPrice()
    {
        totalPrice = price * quantity;
        return totalPrice;
    }

    public double getPriceOverAPeriod() { return priceOverAPeriod; }
    public double getSoldOverAPeriod() { return soldOverAPeriod; }

    public void setSoldOverAPeriod(int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo)
    {
        LocalDate dateFrom = LocalDate.of(yearFrom, monthFrom, dayFrom);
        LocalDate dateTo = LocalDate.of(yearTo, monthTo, dayTo);

        if(soldDate.isAfter(dateFrom) && soldDate.isBefore(dateTo))
            soldOverAPeriod = quantity;
        else if(soldDate.isEqual(dateFrom) || soldDate.equals(dateTo))
            soldOverAPeriod = quantity;
        else
            soldOverAPeriod = 0;

        priceOverAPeriod = price * soldOverAPeriod;
    }

    public void resetStatsOverAPeriod()
    {
        soldOverAPeriod = 0;
        priceOverAPeriod = 0;
    }
}
