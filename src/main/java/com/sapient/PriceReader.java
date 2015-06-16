package com.sapient;

import java.util.List;

public interface PriceReader {
	List<String> readPrice(String connectString);
}
