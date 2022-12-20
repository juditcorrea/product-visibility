package classes;

import java.math.BigInteger;

import com.opencsv.bean.CsvBindByPosition;

public class Stock {
    @CsvBindByPosition(position = 0)
	private int sizeId = -1;
    @CsvBindByPosition(position = 1)
	private BigInteger quantity = new BigInteger("0");
	
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
