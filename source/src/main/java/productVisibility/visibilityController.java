package productVisibility;

import java.util.List;
import java.util.stream.Collectors;

import classes.Product;
import classes.Size;
import classes.Stock;

public class visibilityController {


	/**
	 * Returns a list of the products that are visible according to the specifications.
	 * @param products
	 * @return
	 */
	public static List<Product> getVisbileProducts(List<Product> products) {
		List<Product> productsOnlyNormal = products.stream().filter(Product::filterNormalSize).collect(Collectors.toList());
		List<Product> productsNormalSpecial = products.stream().filter(Product::filterNormalSpecialSize).collect(Collectors.toList());
		
		List<Product> result = productsOnlyNormal.stream().filter(Product::hasAnySize).collect(Collectors.toList());
		result.addAll(productsNormalSpecial.stream().filter(Product::hasAnySizeAndSpecial).collect(Collectors.toList()));

		result.sort((p1, p2) -> p1.getSequence().compareTo(p2.getSequence()));
		return result;
	}

	/**
	 * Match a size with him stock by Id.
	 * @param sizes
	 * @param stocks
	 */
	public static void assignStockToSizes(List<Size> sizes, List<Stock> stocks) {
		sizes.stream().forEach(size ->{
			size.setStock(stocks.stream().filter(stock ->stock.getSizeId() == size.getId()).findFirst().orElse(new Stock()));
		});
		
	}

	/**
	 * Match a product with him sizes by Id
	 * @param products
	 * @param sizes
	 */
	public static void assignSizesToProducts(List<Product> products, List<Size> sizes) {
		products.stream().forEach(product ->{
			product.setSize(sizes.stream().filter(size ->product.getId() == size.getProductId()).collect(Collectors.toList()));
		});
	}
}