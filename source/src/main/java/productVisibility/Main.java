package productVisibility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class Main {
	private final static String productUrl = "source/product.csv";
	private final static String sizeUrl = "source/size.csv";
	private final static String stockUrl = "source/stock.csv";
	
	public static void main(String[] args) throws IOException {
		List<Product> products= (List<Product>) createObjectFromCsv(productUrl, Product.class);
		List<Size> sizes= (List<Size>) createObjectFromCsv(sizeUrl, Size.class);
		List<Stock> stocks= (List<Stock>) createObjectFromCsv(stockUrl, Stock.class);
	}
	
	private static Object createObjectFromCsv(String url, Class clase) throws IllegalStateException, FileNotFoundException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<?> beans = new CsvToBeanBuilder(new FileReader(url))
	                .withType(clase)
	                .build()
	                .parse();

		return beans;
		
	}
}
