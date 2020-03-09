package cxf.frontend.jaxws.codefirst.ex2.context;

import lombok.Data;

@Data
public class Order {

	private String customerId;
	private String itemId;
	private int quantity;
	private double price;

}
