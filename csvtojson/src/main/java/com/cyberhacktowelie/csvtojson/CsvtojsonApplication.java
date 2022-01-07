package com.cyberhacktowelie.csvtojson;
import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvtojsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvtojsonApplication.class, args);

		CsvToJson csvtojason = new CsvToJson();
		for (String a : args){
			String path = a;
			FileReader f = new FileReader();
			File file = f.openFile(path);
			if(f.isCsv(file)){
				System.out.println(csvtojason.csvToJson(file));
			}else{
				System.out.println(file + " no es un csv.");
			}
		}
	}

}
