package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import classes.Product;
import classes.Size;
import classes.Stock;
import productVisibility.csvReader;
import productVisibility.visibilityController;
import static constants.constants.productUrl;
import static constants.constants.sizeUrl;
import static constants.constants.stockUrl;

public class Main {
	
	public static void main(String[] args) throws IOException {
		List<Product> products = initializeData();
		
		List<Product> visibleProducts = visibilityController.getVisbileProducts(products);

		visibleProducts.forEach(product -> System.out.print(product.getId() + ","));
		
	}
	
	private static List<Product> initializeData() throws IllegalStateException, FileNotFoundException {
		List<Product> products= (List<Product>) csvReader.createObjectFromCsv(productUrl, Product.class);
		List<Size> sizes= (List<Size>) csvReader.createObjectFromCsv(sizeUrl, Size.class);
		List<Stock> stocks= (List<Stock>) csvReader.createObjectFromCsv(stockUrl, Stock.class);
		visibilityController.assignStockToSizes(sizes,stocks);
		visibilityController.assignSizesToProducts(products, sizes);
		return products;
		
	}

}
