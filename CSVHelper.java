package com.pixeltrice.springbootimportcsvfileapp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
	private static final Logger logger = LoggerFactory.getLogger(CSVHelper.class);
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Transaction_Ref", "Value_Date", "Payer_Name", "Payer_Account", "Payee_Name", "Payee_Account", "Amount" };

  public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public static List<ModelClass> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
    	logger.info("Entering Upload method");
      List<ModelClass> developerTutorialList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      logger.info("fetching csv records");
      for (CSVRecord csvRecord : csvRecords) {
    	  
    	  //List<Map<String, String>> data = new LinkedList<>();
    	  Map<String,String> data = csvRecord.toMap();
    	  //Map<String, String> dataMap = data.get(0);
    	  String Trans_ref = data.get("Transaction Referance");
    	  String ValueD = data.get("Value Date");
    	  String Payername = data.get("Payer Name");
    	  String Payeramt = data.get("Payer Amount");
    	  String Payeename = data.get("Payee Name");
    	  String Payeeamt = data.get("Payee Amount");
    	  String Amt = data.get("Amount");
    	  logger.info("csv record: " + data);	 
    	  
    	  
    	  
    	  
    	  ModelClass modelclass = new ModelClass(
    			//  Trans_ref,ValueD,Payername,Payeramt,Payeename,Payeeamt,
       
              //Double.parseDouble(Amt)
    			  
            );
    	  modelclass.setTransaction_ref(Trans_ref);
    	  modelclass.setValue_date(ValueD);
    	  modelclass.setPayer_name(Payername);
    	  modelclass.setPayer_account(Payeramt);
    	  modelclass.setPayee_name(Payeename);
    	  modelclass.setPayee_account(Payeeamt);
    	  modelclass.setAmount(Double.parseDouble(Amt));
    	  
    	  logger.info("model class object" + modelclass);
    	  developerTutorialList.add(modelclass);
    	  logger.info("model class list " +  developerTutorialList);
    	
      }

      return developerTutorialList;
    } catch (IOException e) {
    	logger.info("Error {} "  , e.getMessage());
    	throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<ModelClass> developerTutorialList) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (ModelClass developerTutorial : developerTutorialList) {
        List<String> data = Arrays.asList(
              developerTutorial.getTransaction_ref(),
              developerTutorial.getValue_date(),
              developerTutorial.getPayer_name(),
              developerTutorial.getPayer_account(),
              developerTutorial.getPayee_name(),
              developerTutorial.getPayee_account(),
              
              
              String.valueOf(developerTutorial.getAmount())
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}
