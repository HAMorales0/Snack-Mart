package com.techelevator.items;

import java.math.BigDecimal;

public abstract class Product {

    private String name;
    private BigDecimal cost;
    private String menuID;
    private int quantity;
    private String type;

    public Product (String menuID, String name, BigDecimal cost, String type, String quantity) {
        this.menuID = menuID;
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.quantity = Integer.parseInt(quantity);
    }

    public String getName() {return name;}

    public BigDecimal getCost() {return cost;}

    public String getMenuID() {return menuID;}

    public String getType() {return type;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void setCost(BigDecimal cost) {this.cost = cost;}

}
