package com.pixeltrice.springbootimportcsvfileapp;

import java.util.List;

public class ResponseMessage {

	private String message;
	private String noOfValidTXN;
	private String noOfInvalidTXN;
	private String fileDownloadUri;
	private  List<TransactionPojo> pojoList;
	

	  /*public ResponseMessage(String message, String fileDownloadUri) {
	    this.message = message;
	    this.fileDownloadUri = fileDownloadUri;
	  }*/

	  public String getMessage() {
	    return message;
	  }

	  

	public void setMessage(String message) {
	    this.message = message;
	  }

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getNoOfValidTXN() {
		return noOfValidTXN;
	}

	public void setNoOfValidTXN(String noOfValidTXN) {
		this.noOfValidTXN = noOfValidTXN;
	}

	public String getNoOfInvalidTXN() {
		return noOfInvalidTXN;
	}

	public void setNoOfInvalidTXN(String noOfInvalidTXN) {
		this.noOfInvalidTXN = noOfInvalidTXN;
	}

	public List<TransactionPojo> getPojoList() {
		return pojoList;
	}

	public void setPojoList(List<TransactionPojo> pojoList) {
		this.pojoList = pojoList;
	}



	public ResponseMessage(String message, String noOfValidTXN, String noOfInvalidTXN, String fileDownloadUri,
			List<TransactionPojo> pojoList) {
		super();
		this.message = message;
		this.noOfValidTXN = noOfValidTXN;
		this.noOfInvalidTXN = noOfInvalidTXN;
		this.fileDownloadUri = fileDownloadUri;
		this.pojoList = pojoList;
	}

}
