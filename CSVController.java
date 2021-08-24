package com.pixeltrice.springbootimportcsvfileapp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVController {
	private static final Logger logger = LoggerFactory.getLogger(CSVController.class);
  @Autowired
  CSVService fileService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
     String noOfValidTXN = "";
	 String noOfInvalidTXN = "";
    logger.info("Entering Upload method");
    if (CSVHelper.hasCSVFormat(file)) {
      try {
    	//boolean isInputValid = fileService.validateInput(file); 
    	  Map<String,Object> resultMap = fileService.validateInput(file);
    	  boolean isInputValid = (Boolean)resultMap.get("isInputValid"); 
    	  int Valid_TXN = (Integer)resultMap.get("Valid_TXN"); 
    	  int Invalid_TXN = (Integer)resultMap.get("Invalid_TXN"); 
    	  List<TransactionPojo> pojoList = (List<TransactionPojo>) resultMap.get("txnobjlist");
    	  
    	logger.info("isInputValid: " + isInputValid);
    	if(isInputValid)
    	{
    		fileService.save(file);
            logger.info("File Save Completed");

            message = "Uploaded the file successfully ! ";
            noOfValidTXN = "No. of Successful TXN : " + Valid_TXN ; 
            		noOfInvalidTXN = "No. of Unsuccessful TXN : " + Invalid_TXN ;
            logger.info( "No. of Successful TXN : " + Valid_TXN + "No. of Unsuccessful TXN : " + Invalid_TXN);
            /*String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/csv/download/")
                    .path(file.getOriginalFilename())
                    .toUriString();*/

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,noOfValidTXN,noOfInvalidTXN,"", pojoList));
    	}
    	else
    	{
    		message = "Could not upload the file because input file is invalid  " + file.getOriginalFilename() ;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,"","","", null));
    		
    	}
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,"","","", null));
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<ModelClass>> getAllTutorials() {
    try {
      List<ModelClass> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*@GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }*/
}
