/**
 * 
 */
package com.mvp.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mvp.demo.model.Employee;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;



/**
 * @author User
 *
 */
public class CsvUtil {
	Logger LOG = LoggerFactory.getLogger(CsvUtil.class);
	
	public static List<Employee> parseCsvFile(InputStream in)
	{
		String[] CSV_HEADER= {"id","login","name","salary","startDate"};
		Reader reader=null;
		CsvToBean<Employee> csvToBean=null;
		List<Employee> employee=new ArrayList<Employee>();
		
		try {
			reader=new InputStreamReader(in);
			
			ColumnPositionMappingStrategy<Employee> mappingStrategy=new ColumnPositionMappingStrategy<Employee>();
			mappingStrategy.setType(Employee.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
			csvToBean=new CsvToBeanBuilder<Employee>(reader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();
			employee=csvToBean.parse();
			return employee;
			
		}catch (Exception e){
			System.out.println("Error while reading CSV..!");
			e.printStackTrace();
		}finally {
			try {
				reader.close();
				}catch (IOException e) {
					System.out.println("closing csvParser Error!");
				}
		}
		
		return employee;
	}
	public static void employeeToCsv(Writer writer,List<Employee> employee) {
		
		String[] CSV_HEADER= {"id","login","name","salary","startDate"};
		
		StatefulBeanToCsv<Employee> beanToCsv=null;
		
		try {
			//write list of employee obj..
			ColumnPositionMappingStrategy<Employee> mappingStrategy = 
	                new ColumnPositionMappingStrategy<Employee>();
	      
	      mappingStrategy.setType(Employee.class);
	      mappingStrategy.setColumnMapping(CSV_HEADER);
	      
	      beanToCsv = new StatefulBeanToCsvBuilder<Employee>(writer)
	          .withMappingStrategy(mappingStrategy)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();
	 
	      beanToCsv.write(employee);
		}catch (Exception e) {
		      System.out.println("Writing CSV error!");
		      e.printStackTrace();
		    }
		
		
		
	}
}
