package com.sapient;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);
	private PriceReader priceReader;
	private PricePrinter pricePrinter;
	private String priceConnectString;
	private double[]treasuryPriceList;
	
	public Main() {
	}
	
	@Inject
	public void setPriceReader(PriceReader reader) {
		this.priceReader = reader;
	}

	@Inject
	public void setPricePrinter(PricePrinter printer) {
		this.pricePrinter= printer;
	}
	
	public void setPriceConnectString(String priceConnectString) {
		this.priceConnectString = priceConnectString;		
	}
	
	public void printPrices(double[] prices) {
		pricePrinter.printPrices(prices);		
	}

	public List<String> getPriceStrings() {
		return priceReader.readPrice(priceConnectString );	
	}

	public double[]getPrices() {
		return treasuryPriceList;
	}
	
	public void processPrices() {		
		List<String>priceList = getPriceStrings(); 
		treasuryPriceList = convertPrices(priceList);
		sortPrices(treasuryPriceList);
		printPrices(treasuryPriceList);
	}
	

	private void sortPrices(double[] list) {
		if (list != null)
			Arrays.sort(list);		
	}

	
	private double[] convertPrices(List<String> priceList) {
		if (priceList == null)
			return null;
		double[] prices = new double[priceList.size()];
		int i = 0;
		for (String priceStr : priceList) {
			prices[i] = convertTPrice(priceStr);
			i++;
		}
		return prices;
	}

	
	
	
	private float convertTPrice(String priceStr) {
		String[]splits = priceStr.split("[-+]");
		float i = Float.parseFloat(splits[0]) + Float.parseFloat(splits[1])/32 +  (priceStr.endsWith("+")?0.015625f:0.0f);		
		return i;
	}

	
	public static void main(String... args) {
		System.out.println("Program Starting");
		
		Injector injector = Guice.createInjector(new AppInjector());        
         
	    Main app = injector.getInstance(Main.class);
	    app.setPriceConnectString(".\\src\\main\\resources\\bond_prices.txt");
	    app.processPrices();
		System.out.println("Program Complete..Exiting....");
	}


}


/* TODO:
 * --create junit tests
 * --write mockito objects
 * --learn why juice is better than spring
 * --learn agile terminology
 * --learn github and publish project
 * 
 * PARTIALLY COMPLETE
 * --fix the gradle script to use uploadArchives
 * --integrate gradle into eclipse
 * 
 * COMPLETE
 * --integrate junit into eclipse
 * --run the gradle built jar from command line -- runs using gradle
 * --figure out how gradle can provide dependent libraries to you for running -- may be not needed as it runs via gradle
 * --run junit tests via gradle
 * --write the main program
 */
