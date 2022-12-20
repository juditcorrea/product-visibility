package productVisibility;

import java.math.BigInteger;
import java.util.List;

import com.opencsv.bean.CsvBindByPosition;

public class Product {
    @CsvBindByPosition(position = 0)
	private int id;
    @CsvBindByPosition(position = 1)
	private BigInteger sequence;
	private List<Size> sizes;
	
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

	public List<Size> getSize() {
		return sizes;
	}

	public void setSize(List<Size> size) {
		this.sizes = size;
	}
	
	public boolean hasAnySize() {
		return this.getSize().stream().filter(size -> size.getStock().getQuantity().compareTo(new BigInteger("0")) > 0 || size.isBackSoon()).findFirst().isPresent();
	}
	
	public boolean filterNormalSize() {
		return !this.getSize().stream().filter(size -> size.isSpecial()).findFirst().isPresent();
	}
	
	public boolean filterNormalSpecialSize() {
		return this.getSize().stream().filter(size -> size.isSpecial()).findFirst().isPresent();
	}
	
	public boolean hasAnySizeAndSpecial() {
		boolean normalSize = this.getSize().stream().filter(size -> (size.getStock().getQuantity().compareTo(new BigInteger("0"))  > 0 || size.isBackSoon()) && !size.isSpecial()).findFirst().isPresent();
		boolean specialSize = this.getSize().stream().filter(size -> (size.getStock().getQuantity().compareTo(new BigInteger("0"))  > 0 || size.isBackSoon()) && size.isSpecial()).findFirst().isPresent();
		return normalSize && specialSize;
	}

}
