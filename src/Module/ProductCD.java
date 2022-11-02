package Module;

import java.io.Serializable;
import java.time.LocalDate;


public class ProductCD implements Serializable {


    String name;
    String genre;
    String singer;
    int supplierID;
    double price;
    String barcode;//TODO
    int quantity;
    int ID;
    Date expiryDate;//TODO
    LocalDate purchasedDate;

    static int idGenerator = 0;

    public ProductCD()
    {
        ID = ++idGenerator;
        expiryDate = new Date();
        purchasedDate = LocalDate.now();
    }

    public int getID() { return ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getSupplierID() { return supplierID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }


    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }



}
