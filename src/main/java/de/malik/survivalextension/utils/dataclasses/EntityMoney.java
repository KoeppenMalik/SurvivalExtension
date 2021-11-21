// Created on 20.11.2021, 19:12

package de.malik.survivalextension.utils.dataclasses;

public class EntityMoney {

    private String name;
    private int money;

    public EntityMoney(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
