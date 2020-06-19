package com.mthree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.mthree.models.Consumers;
import com.mthree.models.Exchange;
import com.mthree.models.OrderStock;
import com.mthree.models.Sort;
import com.mthree.models.Trader;
import com.mthree.services.SortService;


@SpringBootTest
@RunWith(SpringRunner.class)
class SortSpringApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;


	@Autowired
	private SortService sortService;
	private OrderStock or;
	private Exchange e;
	private Sort s;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		or = new OrderStock();
		e=new Exchange();
		s=new Sort();
	}


	@Test
	void getIdCreation()
	{
		assertNotNull(or.getOrderId());
	}

	@Test
	void stringCreation()
	{
		assertNotNull(s.toString());
	}
	@Test
	void updateStatus()
	{
		assertNotNull(sortService.executeTrade(1,1));
	}


	@Test
	void testCreateOrder() throws Exception {

		OrderStock o1 = new OrderStock();

		o1.setOrderId(1);
		o1.setNumberOfShares(100);
		o1.setOrderExchangeId("lol");
		o1.setTypeOfOrder("NYSE");
		o1.setCompany(null);
		o1.setPrice(1000);
		o1.setTimeStamp(null);
		o1.setExchange(null);
		o1.setOrderStatus(null);
		o1.setConsumers(null);
		String json = new Gson().toJson(o1);


		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/orders/createOrder/NYSE/11")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


	@Test
	void testCreateConsumer() throws Exception {

		Consumers c = new Consumers();

		c.setConsumersId("102");
		c.setFirstName("lol");
		c.setLastName("lol");
		c.setEmail("lol123@.com");
		c.setMobileNumber("528964");
		c.setLocation("sufbnvk");
		c.setExchangeOfConsumers(null);
		c.setTransactedAmountTillNow(null);
		c.setOrdersOfUser(null);
		c.setUserTransactions(null);
		String json = new Gson().toJson(c);


		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/consumers/createconsumer/NYSE")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


	@Test
	void testCreateTrade() throws Exception {

		Trader tr=new Trader();

		tr.setTraderId("1");
		tr.setTraderName("Aashish");
		tr.setEmail("lol");
		tr.setPassword("NYSE");
		tr.setMobileNumber("564654");
		tr.setLocation("India");
		tr.setExchange(null);
		String json = new Gson().toJson(tr);


		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/trader/createtrader/11")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}



}
