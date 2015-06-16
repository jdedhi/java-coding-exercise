package com.sapient.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sapient.AppInjector;
import com.sapient.Main;
import com.sapient.PriceReader;
import com.sun.scenario.effect.impl.state.LinearConvolveRenderState.PassType;

import static org.mockito.Mockito.*;

public class MockitoTest {

	private static final Logger log = LoggerFactory.getLogger(MockitoTest.class);
	Main app;
	@Before
	public void setUp() throws Exception {
		log.info("In setup");

		Injector injector = Guice.createInjector(new AppInjector());                
	    app = injector.getInstance(Main.class);
		List<String> prices = new ArrayList<String>();
		prices.add("99-31");
		prices.add("99-31+");
		prices.add("100-00");
		prices.add("100-01");
		prices.add("100-01+");
		prices.add("100-02");
		
		PriceReader reader = mock(PriceReader.class);
		when(reader.readPrice(anyString())).thenReturn(prices);
		app.setPriceReader(reader);
	}
	@Test
	public void testCompleteApp() {
	    app.processPrices();
	}
	
	@Test
	public void testFileReader() {
		assertTrue(app.getPriceStrings().size() ==6);
	}
	

		
	@Test
	public void testConsoleWrite() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream savedOut = System.out;	
		System.setOut(new PrintStream(outContent));
		app.processPrices();
		System.setOut(savedOut);		
		String expectedStr ="$99.96875\r\n$99.984375\r\n$100.00\r\n$100.03125\r\n$100.046875\r\n$100.0625\r\n";
		assertEquals(expectedStr, outContent.toString());
	}
	


}
