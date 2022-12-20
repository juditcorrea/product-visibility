package productVisibility;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.customconverter.ConvertGermanToBoolean;
import com.opencsv.bean.processor.PreAssignmentProcessor;

public class Size {
    @CsvBindByPosition(position = 0)
	private int id;
    @CsvBindByPosition(position = 1)
	private int productId;

    @CsvBindByPosition(position = 2)
    @PreAssignmentProcessor(processor = ConvertEmptyOrBlankStringsToDefault.class)
    @CsvCustomBindByName(converter = ConvertGermanToBoolean.class)
	private Boolean backSoon;
    @CsvBindByPosition(position = 3)
    @PreAssignmentProcessor(processor = ConvertEmptyOrBlankStringsToDefault.class)
    @CsvCustomBindByName(converter = ConvertGermanToBoolean.class)
	private Boolean special;

	private Stock stock;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public boolean isBackSoon() {
		return backSoon;
	}
	public void setBackSoon(boolean backSoon) {
		this.backSoon = backSoon;
	}
	public boolean isSpecial() {
		return special;
	}
	public void setSpecial(boolean special) {
		this.special = special;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
