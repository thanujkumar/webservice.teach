package cxf.transport.custom_conduit.ex;

import lombok.Data;

@Data
public class TkOrder {

	private String customerId;
	private String itemId;
	private int quantity;
	private double price;

}
