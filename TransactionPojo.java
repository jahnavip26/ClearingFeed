package com.pixeltrice.springbootimportcsvfileapp;

public class TransactionPojo {

	private String txnref;
	private String valueDate;
	private String payerName;
	private String payerAcc;
	private String payeeName;
	private String payeeAcc;
	private String Amount;
	public String getTxnref() {
		return txnref;
	}
	public void setTxnref(String txnref) {
		this.txnref = txnref;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerAcc() {
		return payerAcc;
	}
	public void setPayerAcc(String payerAcc) {
		this.payerAcc = payerAcc;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeAcc() {
		return payeeAcc;
	}
	public void setPayeeAcc(String payeeAcc) {
		this.payeeAcc = payeeAcc;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public TransactionPojo(String txnref, String valueDate, String payerName, String payerAcc, String payeeName,
			String payeeAcc, String amount) {
		super();
		this.txnref = txnref;
		this.valueDate = valueDate;
		this.payerName = payerName;
		this.payerAcc = payerAcc;
		this.payeeName = payeeName;
		this.payeeAcc = payeeAcc;
		Amount = amount;
	}
	public TransactionPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TransactionPojo [txnref=" + txnref + ", valueDate=" + valueDate + ", payerName=" + payerName
				+ ", payerAcc=" + payerAcc + ", payeeName=" + payeeName + ", payeeAcc=" + payeeAcc + ", Amount="
				+ Amount + "]";
	}
}
