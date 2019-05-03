package cxf.frontend.jaxws.contractfirst.ex1;

import java.beans.PropertyDescriptor;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;

public class Client6DynamicUsingServiceModel {

	public static void main(String[] args) throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:9999/cxfws/ex1/OrderProcess3?wsdl");
		Endpoint endpoint = client.getEndpoint();
		
		//Using dynamic service model
		ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
		QName bindingName = new QName("http://order.demo", "OrderProcessServiceSoapBinding");
		BindingInfo binding = serviceInfo.getBinding(bindingName);
		QName opName = new QName("http://order.demo", "processOrder");
		BindingOperationInfo boi = binding.getOperation(opName);
		BindingMessageInfo inputMessageInfo = null;
		
		if (!boi.isUnwrapped()) {
			//OrderProcess uses document literal wrapped style
			inputMessageInfo = boi.getWrappedOperation().getInput();
		} else {
			inputMessageInfo = boi.getUnwrappedOperation().getInput();
		}
		
		List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
		MessagePartInfo partInfo = parts.get(0);
		
		Class<?> orderClass = partInfo.getTypeClass();
		Object orderObj = orderClass.newInstance();
		
		PropertyDescriptor custProp = new PropertyDescriptor("customerID", orderClass);
		custProp.getWriteMethod().invoke(orderObj, "ABC");
		
		PropertyDescriptor itemProp = new PropertyDescriptor("itemID", orderClass);
		itemProp.getWriteMethod().invoke(orderObj, "ed32");
		
		PropertyDescriptor priceProp = new PropertyDescriptor("price", orderClass);
		priceProp.getWriteMethod().invoke(orderObj, 20.67);

		PropertyDescriptor qtyProp = new PropertyDescriptor("qty", orderClass);
		qtyProp.getWriteMethod().invoke(orderObj, Integer.valueOf(10));
		
		Object[] result = client.invoke(opName, orderObj);
		System.out.println("The order ID is " + result[0]);

	}
}
