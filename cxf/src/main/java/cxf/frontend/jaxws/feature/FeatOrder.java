package cxf.frontend.jaxws.feature;

import lombok.Data;

@Data
public class FeatOrder {

	private String customerId;
	private String itemId;
	private int quantity;
	private double price;

}
