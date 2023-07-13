package Tugas_PBO_DIVA;

import java.util.ArrayList;
import java.util.HashMap;


public class Order {

    private Restaurant restaurant;
    private HashMap<Menu, Integer> menus;
    private int distance;

    public Order(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.menus = new HashMap<>();
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public HashMap<Menu, Integer> getMenus() {
        return this.menus;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : this.menus.keySet()) {
            totalPrice += menu.getHarga() * this.menus.get(menu);
        }
        totalPrice += this.distance * 1000;
        return totalPrice;
    }

    public void addMenu(Menu menu, int quantity) {
        if (this.menus.containsKey(menu)) {
            int currentQuantity = this.menus.get(menu);
            this.menus.put(menu, currentQuantity + quantity);
        } else {
            this.menus.put(menu, quantity);
        }
    }


}