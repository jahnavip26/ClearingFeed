package com.pixeltrice.springbootimportcsvfileapp;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
	private static final Logger logger = LoggerFactory.getLogger(CSVService.class);
  @Autowired
  RepositoryClass repository;
  
  TransactionPojo txnObj = new TransactionPojo();

  public void save(MultipartFile file) {
    try {
    	
      //List<modelclass> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
    	List<ModelClass> tutorials = new ArrayList<>();
      repository.saveAll(tutorials);
    } catch (Exception e) {
      logger.error(e.getMessage());
    	//throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<ModelClass> tutorials = repository.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
    return in;
  }

  public List<ModelClass> getAllTutorials() {
    return repository.findAll();
  }
  
  
  public Map<String,Object> validateInput (MultipartFile csvMultipartFile) {
	
	  boolean isInputValid = false;
	  List<Boolean> inputValidationList = new ArrayList<Boolean>();
	  Map<String,Object> resultMap = new HashMap<>();
	  List<String> validTransList = new ArrayList<String>();

	  List<String> inValidTransList = new ArrayList<String>();
	  List<TransactionPojo> txnPojoList = new ArrayList<>();
	//Get scanner instance
      Scanner scanner;
	try {
		File csvFile = CSVService.convert(csvMultipartFile);
		scanner = new Scanner(csvFile);
		//Set the delimiter used in file
	      scanner.useDelimiter(",");
	       
	      //Get all tokens and store them in some data structure
	      //I am just printing them
	      
	      String row = "";
	      //int count = 0;
	      
	      BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
	      while ((row = csvReader.readLine()) != null) {
	    	  TransactionPojo txnObj = new TransactionPojo();
	    	  //logger.info("reading input: " + csvReader.readLine());
	    	  
	          String[] strArray  = row.split(",");
	          txnObj.setTxnref(strArray[0]);
	          txnObj.setValueDate(strArray[1]);;
	          txnObj.setPayerName(strArray[2]);
	          txnObj.setPayerAcc(strArray[3]);
	          txnObj.setPayeeName(strArray[4]);
	          txnObj.setPayeeAcc(strArray[5]);
	          txnObj.setAmount(strArray[6]);
	          txnPojoList.add(txnObj);
	          int count = 0;
	          
	          for(String str : strArray){
	        	  
	        	  //System.out.print(scanner.next() + "|");
		         Map<String,Object> result= validateParameter(str, count);
		         isInputValid = (boolean) result.get("isValid");
		         txnObj = (TransactionPojo) result.get("txnObj");
		        
		          inputValidationList.add(isInputValid);
		          if(!isInputValid) {
		        	  inValidTransList.add(row);
		          }
		          
		          count++;
		          
	        	  
	          }
	          if(isInputValid) {
	        	  validTransList.add(row);
	          }
	          else {
	        	  inValidTransList.add(row);
	          }
	          
	          
	        
	          // do something with the data
	      }
	      csvReader.close();
	      
	     
		
	      
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		
	}
	resultMap.put("Valid_TXN", validTransList.size()-inValidTransList.size());
	resultMap.put("Invalid_TXN", inValidTransList.size());
	resultMap.put("isInputValid", isInputValid);
	resultMap.put("txnobjlist", txnPojoList);
	if(inputValidationList.contains(false))
	{
		isInputValid = false;
	}
	else
	{
		isInputValid = true;
	}
	return resultMap;
       
      
	  
  }

private Map<String,Object> validateParameter(String input, int count) {
	// TODO Auto-generated method stub
	
	boolean isValid = false;
	Map<String,Object> result = new HashMap<>();
	TransactionPojo txnObj = new TransactionPojo();
	result.put("isValid", isValid);
	result.put("txnObject", txnObj);
	
	
	if(count == 0)
	{
		isValid = input.length()>0 && input.length()<=12 ? true: false;
		txnObj.setTxnref(input);
	}
	else if(count == 1)
	{
		txnObj.setValueDate(input);
		isValid = input.length()==8? true: false;
		
		SimpleDateFormat sdfrmt = new SimpleDateFormat("ddMMyyyy");
	    sdfrmt.setLenient(false);
	    /* Create Date object
	     * parse the string into date 
             */
	    try
	    {
	       // Date javaDate = sdfrmt.parse(input); 
	        sdfrmt.parse(input); 
	        //logger.info(input+" is valid date format");
	        isValid = true;
	    }
	    /* Date format is invalid */
	    
	     catch (java.text.ParseException e) {
			
			
		        //logger.info(input+" is Invalid Date format");
		        isValid = false;
		        //logger.error("invalid value encountered :" + input);
			
		}
	    
	    
	    /* Return true if date format is valid */
	    
	    
	}
	    
	    else if(count == 2)
	    {
	    	txnObj.setPayerName(input);
	    	logger.info("name length : " + input.length());
	   
	    	isValid = input.length()>0 && input.length()<=35 ? true: false;
	    	 
	        boolean isAlphaNumeric = input != null &&
	                                input.chars().allMatch(Character::isLetterOrDigit);
	        //System.out.println("IsAlphaNumeric: " + isAlphaNumeric);
	    	isValid = isAlphaNumeric;
	    }
	    
	    else if(count == 3)
	    {
	    	txnObj.setPayerAcc(input);
	    	isValid = input.length()>0 && input.length()<=12 ? true: false;
	    	 
	        boolean isAlphaNumeric = input != null &&
	                                input.chars().allMatch(Character::isLetterOrDigit);
	        //System.out.println("IsAlphaNumeric: " + isAlphaNumeric);
	        isValid = isAlphaNumeric;
	    }
	    
	    else if(count == 4)
	    {
	    	isValid = input.length()>0 && input.length()<=35 ? true: false;
	    	 txnObj.setPayeeName(input);
	        boolean isAlphaNumeric = input != null &&
	                                input.chars().allMatch(Character::isLetterOrDigit);
	        //System.out.println("IsAlphaNumeric: " + isAlphaNumeric);
	        isValid = isAlphaNumeric;
	    	
	    }
	    
	    else if(count == 5)
	    {
	    	isValid = input.length()>0 && input.length()<=12 ? true: false;
	    	 txnObj.setPayeeAcc(input);
	        boolean isAlphaNumeric = input != null &&
	                                input.chars().allMatch(Character::isLetterOrDigit);
	       // System.out.println("IsAlphaNumeric: " + isAlphaNumeric);
	        isValid = isAlphaNumeric;
	    }
	    
	    else if(count == 6)
	    {
	    	txnObj.setAmount(input);
	    	Double d = Double.parseDouble(input);
	    	String[] splitter = d.toString().split("\\.");
	    	int beforeDecimal = splitter[0].length();   // Before Decimal Count
	    	int afterDecimal = splitter[1].length();  // After Decimal Count

	    	if (beforeDecimal >0 && beforeDecimal <= 10 
	    			&& afterDecimal >0 && afterDecimal <= 2)
	    	{
	    		isValid = true;
	    	}
	    	   // valid
	    	else
	    	{
	    		isValid = false;
	    	}
	    	   // invalid
	    }
	    
	    
	
	
if(isValid)
{
	logger.info("valid value encountered :" + input);
	
	
}

else
{

	logger.error("invalid value encountered :" + input);
	
}
System.out.println("Input: "+input+"TXNObj: "+txnObj);
result.put("isValid", isValid);
result.put("txnObject", txnObj);
	
	return result;
	
}

public static File convert(MultipartFile file) throws IOException {
	File convFile = new File(file.getOriginalFilename());
	convFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(convFile);
	fos.write(file.getBytes());
	fos.close();
	return convFile;
}


}

//  singleFileUploadSuccess.innerHTML = "<p>"+(response && response.message)+"</p>";
