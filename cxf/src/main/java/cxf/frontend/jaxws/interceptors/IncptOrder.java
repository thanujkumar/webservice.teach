package cxf.frontend.jaxws.interceptors;

import lombok.Data;

@Data
public class IncptOrder {

	private String customerId;
	private String itemId;
	private int quantity;
	private double price;

}
