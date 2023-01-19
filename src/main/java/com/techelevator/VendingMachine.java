package com.techelevator;

import com.techelevator.items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {
    private List<Product> listOfItems;
    private BigDecimal userBalance;
    private BigDecimal totalSales;
    private Map<String, Integer> salesReport = new HashMap<>();
    private Logger logger = new Logger();

    Scanner amountToDeposit = new Scanner(System.in);
    Scanner desiredProduct = new Scanner(System.in);


    public VendingMachine() {
        this.userBalance = new BigDecimal("0.00");
        this.totalSales = new BigDecimal("0.00");

        listOfItems = new ArrayList<>();
        File newFile = new File("vendingmachine.csv");
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(newFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] productLineSplit = line.split("\\|");
                String type = productLineSplit[3];
                BigDecimal itemCost = new BigDecimal(productLineSplit[2]);
                String quantity = "5";

                if (type.equals("Chip")) {
                    listOfItems.add(new Chips(productLineSplit[0], productLineSplit[1], itemCost, productLineSplit[3], quantity));
                    salesReport.put(productLineSplit[1], 0);

                } else if (type.equals("Candy")) {
                    listOfItems.add(new Candy(productLineSplit[0], productLineSplit[1], itemCost, productLineSplit[3], quantity));
                    salesReport.put(productLineSplit[1], 0);

                } else if (type.equals("Drink")) {
                    listOfItems.add(new Drinks(productLineSplit[0], productLineSplit[1], itemCost, productLineSplit[3], quantity));
                    salesReport.put(productLineSplit[1], 0);

                } else if (type.equals("Gum")) {
                    listOfItems.add(new Gum(productLineSplit[0], productLineSplit[1], itemCost, productLineSplit[3], quantity));
                    salesReport.put(productLineSplit[1], 0);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public List<Product> getListOfItems() {
        return listOfItems;
    }

    public void printListOfItems() {

        System.out.println("--------------------------\n");
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println(String.format("%s %-20s $%4.2f %10s %20s %s", listOfItems.get(i).getMenuID(), listOfItems.get(i).getName(),
                    listOfItems.get(i).getCost(), listOfItems.get(i).getType(), "Available:", listOfItems.get(i).getQuantity()));
        }
        System.out.println("---------------------------------------------------------------\n");
    }
    public void depositMoney() { //update userBalance and log deposit

        System.out.println("--------------------------\n");
        System.out.println("How much would you like to deposit? \n\nType 1 for $1.00, \nType 5 for $5.00 \nType 10 for $10.00\n");
        int depositAmount = amountToDeposit.nextInt();
        if (depositAmount == 1) {
            userBalance = userBalance.add(BigDecimal.valueOf(1));
            System.out.println("\nYou entered $1.00 - Current balance: " + userBalance);
            logger.logDeposits(BigDecimal.valueOf(1), userBalance);
        } else if (depositAmount == 5) {
            userBalance = userBalance.add(BigDecimal.valueOf(5));
            System.out.println("\nYou entered $5.00 - Current balance: " + userBalance);
            logger.logDeposits(BigDecimal.valueOf(5), userBalance);
        } else if (depositAmount == 10) {
            userBalance = userBalance.add(BigDecimal.valueOf(10));
            System.out.println("\nYou entered $10.00 - Current balance: " + userBalance);
            logger.logDeposits(BigDecimal.valueOf(10), userBalance);
        } else {
            System.out.println("\nPlease select one of 3 options");
        }
    }

    public void purchaseItems() { //make purchase, log purchase and update inventory

    printListOfItems();
    boolean inStock = false;
    boolean validMenuId = false;
        System.out.println("Please enter product's code. Ex A1\n");
        String codeSelected = desiredProduct.nextLine();

        for (int i = 0; i < getListOfItems().size(); i++) {
            if (codeSelected.contains(getListOfItems().get(i).getMenuID())) {
                validMenuId = true;
                BigDecimal productCost = getListOfItems().get(i).getCost();
                int productQuantity = getListOfItems().get(i).getQuantity();

                if (productQuantity > 0) {
                    inStock = true;
                } else if (!inStock){
                    System.out.println("Product is out of stock, please choose another item");
                    break;
                }
                if (userBalance.compareTo(productCost) >= 0) {
                    userBalance = userBalance.subtract(productCost);
                    getListOfItems().get(i).setQuantity(getListOfItems().get(i).getQuantity() - 1); //updating product quantity

                    String code = getListOfItems().get(i).getMenuID();
                    String name = getListOfItems().get(i).getName();
                    BigDecimal cost = getListOfItems().get(i).getCost();
                    String type = getListOfItems().get(i).getType();
                    logger.logPurchases(code, name, cost, userBalance); //logging purchases to Log.txt
                    logger.log.flush();
                    if (type.equals("Chip")) {
                        System.out.println("\n" + name + " cost you a total of " + cost);
                        System.out.println("\nYour remaining balance is $" + userBalance);
                        System.out.println("\nCrunch Crunch, Yum!");
                        break;
                    } else if (type.equals("Candy")) {
                        System.out.println("\n" + name + " cost you a total of " + cost);
                        System.out.println("\nYour remaining balance is $" + userBalance);
                        System.out.println("\nMunch Munch, Yum!");
                        break;
                    } else if (type.equals("Drink")) {
                        System.out.println("\n" + name + " cost you a total of " + cost);
                        System.out.println("\nYour remaining balance is $" + userBalance);
                        System.out.println("\nGlug Glug, Yum!");
                        break;
                    } else if (type.equals("Gum")) {
                        System.out.println("\n" + name + " cost you a total of " + cost);
                        System.out.println("\nYour remaining balance is $" + userBalance);
                        System.out.println("\nChew Chew, Yum!");
                        break;
                    }
                } else {
                    System.out.println("Not enough funds, please Feed Money or select other product:");
                    break;
                }
            }
        } if (!validMenuId) {
            System.out.println("Invalid entry, please try again");
            }
        }
}
