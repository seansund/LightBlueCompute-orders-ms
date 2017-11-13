package orders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import orders.models.Orders;

public class OrdersControllerTest {
	
	
	@Test
	public void testMarshalToJson() throws Exception {
		final Orders inv = new Orders();
		final Random rnd = new Random();
		
		int count = 5;
		String customerId = "1234";
		long itemId = rnd.nextLong();
		long orderId = rnd.nextLong();
		
		final ObjectMapper mapper = new ObjectMapper();
		
		
		inv.setCount(count);
		inv.setCustomerId(customerId);
		inv.setItemId(itemId);
		inv.setOrderId(orderId);
		
		final String json = mapper.writeValueAsString(inv);
		
		// construct a json string with the above properties
		
		final StringBuilder myJsonStr = new StringBuilder();
		
		myJsonStr.append("{");
		myJsonStr.append("\"count\":").append(count).append(",");
		myJsonStr.append("\"customerId\":").append("\"").append(customerId).append("\",");
		myJsonStr.append("\"itemId\":").append(itemId).append(",");
		myJsonStr.append("\"orderId\":").append(orderId);
		myJsonStr.append("}");
		
		final String myJson = myJsonStr.toString();
		System.out.println("Marshalled Inventory to JSON:" + myJson);
		System.out.println("My JSON String:" + myJson);
		
		final JsonNode jsonObj = mapper.readTree(json);
		final JsonNode myJsonObj = mapper.readTree(myJson);
		
		
		assert(jsonObj.equals(myJsonObj));
		
		
	}
	
	@Test
	public void testMarshalFromJson() throws Exception {
		final Random rnd = new Random();
		
		int count = 5;
		String customerId = "1234";
		long itemId = rnd.nextLong();
		long orderId = rnd.nextLong();
		
		final ObjectMapper mapper = new ObjectMapper();
		
		// construct a json string with the above properties
		
		final StringBuilder myJsonStr = new StringBuilder();
		
		myJsonStr.append("{");
		myJsonStr.append("\"count\":").append(count).append(",");
		myJsonStr.append("\"customerId\":").append("\"").append(customerId).append("\",");
		myJsonStr.append("\"itemId\":").append(itemId).append(",");
		myJsonStr.append("\"orderId\":").append(orderId);
		myJsonStr.append("}");
		
		final String myJson = myJsonStr.toString();
		System.out.println("My JSON String:" + myJson);
		
		// marshall json to Orders object
		
		final Orders order = mapper.readValue(myJson, Orders.class);
		
		// make sure all the properties match up
		assertThat(order.getCount(), is(equalTo(count)));
		assertThat(order.getCustomerId(), is(equalTo(customerId)));
		assertThat(order.getItemId(), is(equalTo(itemId)));
		assertThat(order.getOrderId(), is(equalTo(orderId)));
	}
}
