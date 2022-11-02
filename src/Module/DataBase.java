package Module;

import java.io.*;
import java.util.ArrayList;

public class DataBase {

    static String cashiersPath = "C:\\Users\\user\\IdeaProjects\\CDstore\\resources\\Employees\\Cashiers.ser";
    static String managersPath = "C:\\Users\\user\\IdeaProjects\\CDstore\\resources\\Employees\\Managers.ser";
    static String genrePath = "C:\\Users\\user\\IdeaProjects\\CDstore\\resources\\Statistics\\Genre.ser";
    static String productsCDPath = "C:\\Users\\user\\IdeaProjects\\CDstore\\resources\\Statistics\\ProductsCD.ser";
    static String boughtProdsPath = "C:\\Users\\user\\IdeaProjects\\CDstore\\resources\\Statistics\\BoughtProducts.ser";



    static ArrayList<Cashier> cashiers;
    static ArrayList<Manager> managers;
    static ArrayList<String> genre;
    static ArrayList<ProductCD> productsCD;
    static ArrayList<BoughtProduct> boughtProducts;



    public static ArrayList<Cashier> getCashiers() {
        return cashiers;
    }

    public static ArrayList<Manager> getManager() {
        return managers;
    }

    public static ArrayList<String> getGenre() { return genre; }
    public static ArrayList<ProductCD> getProductsCD() { return productsCD; }
    public static ArrayList<BoughtProduct> getBoughtProducts() { return boughtProducts; }

    //Default Constructor
    public static void putDataInLists() {

        cashiers = new ArrayList<>();
        managers = new ArrayList<>();
        productsCD = new ArrayList<>();
        boughtProducts = new ArrayList<>();
        genre = new ArrayList<>();


        load();

    }




    //Read from file

    public static void load() {
        try {


            ObjectInputStream inCashiers = new ObjectInputStream(new FileInputStream(cashiersPath));
            ObjectInputStream inManagers = new ObjectInputStream(new FileInputStream(managersPath));
            ObjectInputStream inGenre = new ObjectInputStream(new FileInputStream(genrePath));
            ObjectInputStream inProductsCD = new ObjectInputStream(new FileInputStream(productsCDPath));


            ObjectInputStream inBoughtProducts = new ObjectInputStream(new FileInputStream(boughtProdsPath));


            cashiers = (ArrayList<Cashier>)inCashiers.readObject();
            managers = (ArrayList<Manager>)inManagers.readObject();
            genre = (ArrayList)inGenre.readObject();
            productsCD = (ArrayList<ProductCD>)inProductsCD.readObject();
            boughtProducts = (ArrayList<BoughtProduct>)inBoughtProducts.readObject();




            inCashiers.close();
            inManagers.close();
            inGenre.close();
            inProductsCD.close();
            inBoughtProducts.close();



        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught.\nWasn't able to load database. You are missing files.");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }


    }


    //Write to file

    public static void save()
    {
        try
        {
            FileOutputStream fosCashiers = new FileOutputStream(cashiersPath);
            ObjectOutputStream oosCashiers = new ObjectOutputStream(fosCashiers);

            FileOutputStream fosManagers = new FileOutputStream(managersPath);
            ObjectOutputStream oosManagers = new ObjectOutputStream(fosManagers);

            FileOutputStream fosProductsCD = new FileOutputStream(productsCDPath);
            ObjectOutputStream oosProductsCD = new ObjectOutputStream(fosProductsCD);

            FileOutputStream fosProdsBought = new FileOutputStream(boughtProdsPath);
            ObjectOutputStream oosProdsBought = new ObjectOutputStream(fosProdsBought);

            FileOutputStream fosGenre = new FileOutputStream(genrePath);
            ObjectOutputStream oosGenre = new ObjectOutputStream(fosGenre);



            oosCashiers.writeObject(cashiers);
            oosManagers.writeObject(managers);
            oosProductsCD.writeObject(productsCD);
            oosProdsBought.writeObject(boughtProducts);
            oosGenre.writeObject(genre);


            oosCashiers.close();
            fosCashiers.close();

            oosManagers.close();
            fosManagers.close();

            oosProductsCD.close();
            fosProductsCD.close();

            oosProdsBought.close();
            fosProdsBought.close();

            oosGenre.close();
            fosGenre.close();


        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        System.out.println("Saved");
    }

}
