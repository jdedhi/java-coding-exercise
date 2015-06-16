package com.sapient;

import java.text.DecimalFormat;
/*
 * Prints the treasury prices to console
 */
public class ConsolePricePrinter implements PricePrinter {
	
	//Format to at least two decimal places or more.
	private DecimalFormat df = new DecimalFormat("$###.00####");
	
	@Override
	public void printPrices(double[] prices) {	
		if (prices == null)
			return;
		for (double price: prices) {		
			System.out.println(df.format(price));
		}
	}

}
