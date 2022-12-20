package productVisibility;

import java.math.BigInteger;

import com.opencsv.bean.CsvBindByPosition;

public class Stock {
    @CsvBindByPosition(position = 0)
	private int sizeId;
    @CsvBindByPosition(position = 1)
	private BigInteger quantity;
	
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public BigInteger getQuantity() {
		return quantity;
	}
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}
	
}
