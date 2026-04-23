package com.ecom.inventory.model;

import jakarta.persistence.*;

@Entity
@Table(name="t_inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;

    public Inventory(){

    }

    public Inventory(Integer quantity, String skuCode) {
        this.quantity = quantity;
        this.skuCode = skuCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
