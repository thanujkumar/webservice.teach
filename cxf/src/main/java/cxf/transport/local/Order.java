package cxf.transport.local;

import lombok.Data;

@Data
public class Order {

	private String customerId;
	private String itemId;
	private int quantity;
	private double price;

}
