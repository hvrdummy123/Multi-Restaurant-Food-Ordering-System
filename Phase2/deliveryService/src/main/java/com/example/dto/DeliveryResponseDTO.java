package com.example.dto;

import com.example.entity.Delivery;

public class DeliveryResponseDTO {
    private Delivery delivery;
    private OrderDTO order;

    public DeliveryResponseDTO(Delivery delivery, OrderDTO order) {
        this.delivery = delivery;
        this.order = order;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
}