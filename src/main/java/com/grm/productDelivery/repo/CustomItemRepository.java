package com.grm.productDelivery.repo;

public interface CustomItemRepository {
    public abstract void updateItemQuantity(String name, float newQuantity);
}
