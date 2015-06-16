package com.sapient.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sapient.AppInjector;
import com.sapient.Main;

public class TestMainApp {
	Main app ;
	
	private static final Logger log = LoggerFactory.getLogger(TestMainApp.class);
	
	@Before
	public void setUp() throws Exception {
		log.info("In setup");

		Injector injector = Guice.createInjector(new AppInjector());                
	    app = injector.getInstance(Main.class);
		//app.setPriceConnectString("c:\\Users\\jdedhi\\My Documents\\java-coding-exercise\\coding-exercise\\src\\main\\resources\\bond_prices.txt");
		app.setPriceConnectString(".\\src\\main\\resources\\bond_prices.txt");
	}

	@Test
	public void testCompleteApp() {
	    app.processPrices();
	}
	
	@Test
	public void testFileReader() {
		assertTrue(app.getPriceStrings().size() > 0);
	}
	

	@Test
	public void testEmptyFile() {
		app.setPriceConnectString(null);
		assertNull(app.getPriceStrings());
	}
	
	@Test
	public void testConsoleWrite() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream savedOut = System.out;	
		System.setOut(new PrintStream(outContent));
		double[] prices = { 101.296875, 100.123,99.1,88.12334};
		app.printPrices(prices);
		System.setOut(savedOut);		
		String expectedStr ="$101.296875\r\n$100.123\r\n$99.10\r\n$88.12334\r\n";
		assertEquals(expectedStr, outContent.toString());
	}
	

}
