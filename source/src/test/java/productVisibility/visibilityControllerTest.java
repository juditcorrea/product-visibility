package productVisibility;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.Product;
import classes.Size;
import classes.Stock;
import static constants.constants.productUrl;
import static constants.constants.sizeUrl;
import static constants.constants.stockUrl;

public class visibilityControllerTest {
	private List<Product> products;
	private List<Size> sizes;
	private List<Stock> stocks;
	
	@Before
	public void init() throws IllegalStateException, FileNotFoundException {
		products = (List<Product>) csvReader.createObjectFromCsv(productUrl, Product.class);
		sizes = (List<Size>) csvReader.createObjectFromCsv(sizeUrl, Size.class);
		stocks = (List<Stock>) csvReader.createObjectFromCsv(stockUrl, Stock.class);
	}
	
	@Test
	public void assignStockToSizesTest() {
		visibilityController.assignStockToSizes(sizes,stocks);
		sizes.stream().forEach(size ->{
			if(size.getStock().getSizeId() != -1)
				assertTrue(size.getId() == size.getStock().getSizeId());
		});
		
	}
	
	@Test
	public void assignSizesToProductsTest() {
		visibilityController.assignSizesToProducts(products, sizes);
		products.stream().forEach(product ->{
			product.getSize().stream().forEach(size->{
				assertTrue(size.getProductId() == product.getId());
			});
		});
		
	}
	
	@Test
	public void getVisbileProductsTest() {
		visibilityController.assignStockToSizes(sizes,stocks);
		visibilityController.assignSizesToProducts(products, sizes);
		List<Product> visibleProducts = visibilityController.getVisbileProducts(products);
		assertTrue(visibleProducts.get(0).getId() == 5);
		assertTrue(visibleProducts.get(1).getId() == 1);
		assertTrue(visibleProducts.get(2).getId() == 3);
		
	}

}
