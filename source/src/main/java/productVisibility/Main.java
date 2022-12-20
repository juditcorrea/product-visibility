package productVisibility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

import classes.Product;
import classes.Size;
import classes.Stock;

public class Main {
	private final static String productUrl = "source/product.csv";
	private final static String sizeUrl = "source/size.csv";
	private final static String stockUrl = "source/stock.csv";
	
	public static void main(String[] args) throws IOException {
		List<Product> products= (List<Product>) createObjectFromCsv(productUrl, Product.class);
		List<Size> sizes= (List<Size>) createObjectFromCsv(sizeUrl, Size.class);
		List<Stock> stocks= (List<Stock>) createObjectFromCsv(stockUrl, Stock.class);
		
		assignStockToSizes(sizes,stocks);
		assignSizesToProducts(products, sizes);
		
		List<Product> visibleProducts = getVisbileProducts(products);

		visibleProducts.sort((p1, p2) -> p1.getSequence().compareTo(p2.getSequence()));
		visibleProducts.forEach(product -> System.out.print(product.getId() + ","));
		
	}
	
	/**
	 * Returns a list of the products that are visible according to the specifications.
	 * @param products
	 * @return
	 */
	private static List<Product> getVisbileProducts(List<Product> products) {
		List<Product> productsOnlyNormal = products.stream().filter(Product::filterNormalSize).collect(Collectors.toList());
		List<Product> productsNormalSpecial = products.stream().filter(Product::filterNormalSpecialSize).collect(Collectors.toList());
		
		List<Product> result = productsOnlyNormal.stream().filter(Product::hasAnySize).collect(Collectors.toList());
		result.addAll(productsNormalSpecial.stream().filter(Product::hasAnySizeAndSpecial).collect(Collectors.toList()));
		
		return result;
	}

	/**
	 * Match a size with him stock by Id.
	 * @param sizes
	 * @param stocks
	 */
	private static void assignStockToSizes(List<Size> sizes, List<Stock> stocks) {
		sizes.stream().forEach(size ->{
			size.setStock(stocks.stream().filter(stock ->stock.getSizeId() == size.getId()).findFirst().orElse(new Stock()));
		});
		
	}

	/**
	 * Match a product with him sizes by Id
	 * @param products
	 * @param sizes
	 */
	private static void assignSizesToProducts(List<Product> products, List<Size> sizes) {
		products.stream().forEach(product ->{
			product.setSize(sizes.stream().filter(size ->product.getId() == size.getProductId()).collect(Collectors.toList()));
		});
	}

	/**
	 * Create a object from a CSV file and convert to given class
	 * @param url
	 * @param clase
	 * @return
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	private static Object createObjectFromCsv(String url, Class clase) throws IllegalStateException, FileNotFoundException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<?> beans = new CsvToBeanBuilder(new FileReader(url))
	                .withType(clase)
	                .build()
	                .parse();

		return beans;
		
	}
}
