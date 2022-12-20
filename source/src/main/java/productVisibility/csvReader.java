package productVisibility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class csvReader {

	/**
	 * Create a object from a CSV file and convert to given class
	 * @param url
	 * @param clase
	 * @return
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public static Object createObjectFromCsv(String url, Class clase) throws IllegalStateException, FileNotFoundException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<?> beans = new CsvToBeanBuilder(new FileReader(url))
	                .withType(clase)
	                .build()
	                .parse();

		return beans;
		
	}

}
