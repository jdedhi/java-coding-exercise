package com.sapient;

import com.google.inject.AbstractModule;


/*
 * bindings for google guice ioc
 */
public class AppInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(PriceReader.class).to(FilePriceReader.class);
		bind(PricePrinter.class).to(ConsolePricePrinter.class);
	}

}
