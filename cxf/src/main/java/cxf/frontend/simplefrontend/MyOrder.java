package cxf.frontend.simplefrontend;

import lombok.Data;

@Data
public class MyOrder {
    private String customerId;
    private String itemId;
    private int quantity;
    private double price;
}
