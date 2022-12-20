package productVisibility;

import java.math.BigInteger;

import com.opencsv.bean.CsvBindByPosition;

public class Product {
    @CsvBindByPosition(position = 0)
	private int id;
    @CsvBindByPosition(position = 1)
	private BigInteger sequence;
	private Size size;
	private Stock stock;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getSequence() {
		return sequence;
	}

	public void setSequence(BigInteger sequence) {
		this.sequence = sequence;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
