package com.example.fantgo.model;

public class Item {
    private int id;
    private String itemName;
    private String description;
    private int price;
    private boolean sold;
    private int userid;


    //Constructor for whenever you receive items from API
    public Item(int id, String itemName, String description, int price)
    {
        this.id = id;
        this.description = description;
    this.itemName = itemName;
    this.price = price;
    this.price = price;

    }
    //Constructor for adding item through additemfragment. Removed ID because ID is generated in the REST API
    public Item(String itemName, String description, int price){
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                ", userid=" + userid +
                '}';
    }
}


