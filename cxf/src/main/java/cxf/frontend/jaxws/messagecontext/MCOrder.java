package cxf.frontend.jaxws.messagecontext;

import lombok.Data;

@Data
public class MCOrder {

	private String customerId;
	private String itemId;
	private int quantity;
	private double price;

}
