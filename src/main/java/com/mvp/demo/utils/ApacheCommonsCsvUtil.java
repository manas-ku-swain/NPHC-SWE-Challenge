package com.mvp.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


import com.mvp.demo.model.Employee;
import com.mvp.demo.services.CsvFileServices;

public class ApacheCommonsCsvUtil {
	Logger LOG = LoggerFactory.getLogger(ApacheCommonsCsvUtil.class);
	private static String csvExtension = "csv";
	
	public static void customersToCsv(Writer writer, List<Employee> employees) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("id","login", "name", "salary", "startDate"));) {
			for (Employee employee : employees) {
				List<String> data = Arrays.asList(employee.getId(),employee.getLogin(), employee.getName(),
						String.valueOf(employee.getSalary()), employee.getStartDate());

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

	public static List<Employee> parseCsvFile(InputStream is) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;

		List<Employee> employees = new ArrayList<Employee>();

		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
			/**	Employee employee = new Employee(csvRecord.get("id"), csvRecord.get("login"),csvRecord.get("name"),
						csvRecord.get("salary"), csvRecord.get("startDate"));**/
				Employee employee = new Employee(csvRecord.get("id"), csvRecord.get("login"),csvRecord.get("name"),
						Double.parseDouble(csvRecord.get("salary")), csvRecord.get("startDate"));
				

				employees.add(employee);
			}

		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}

		return employees;
	}
	
	public static boolean isCSVFile(MultipartFile file) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		
		if(!extension.equals(csvExtension)) {
			return false;
		}
		
		return true;
	}

}
