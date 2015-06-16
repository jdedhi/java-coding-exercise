package com.sapient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Read the treasury prices from the file.
 */
@Singleton
public class FilePriceReader implements PriceReader {

	static final Logger log = LoggerFactory.getLogger(FilePriceReader.class);
	@Override
	public List<String> readPrice(String pathToFile) {
		if (pathToFile == null)
			return null;
		try {
			return Files.readAllLines(new File(pathToFile).toPath());
		} catch (IOException e) {
			log.error("Error while reading price file:"+pathToFile, e);			
			return null;
		}
	}

}
