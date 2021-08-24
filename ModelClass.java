package com.pixeltrice.springbootimportcsvfileapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "csvfileapp")
public class ModelClass {

	  @Id
	  @Column(name = "transaction_ref")
	  private String transaction_ref;

	  @Column(name = "value_date")
	  private String value_date;

	  @Column(name = "payer_name")
	  private String payer_name;

	  @Column(name = "payer_account")
	  private String payer_account;
	  
	  @Column(name = "payee_name")
	  private String payee_name;
	  
	  @Column(name = "payee_account")
	  private String payee_account;
	  
	  @Column(name = "amount")
	  private double amount;

	  public ModelClass() {

	  }

	  public ModelClass(String transaction_ref, String value_date, String payer_name, String payer_account, String payee_name, String payee_account, double amount) {
	    this.transaction_ref = transaction_ref;
	    this.value_date = value_date;
	    this.payer_name = payer_name;
	    this.payer_account = payer_account;
	    this.payee_name = payee_name;
	    this.payee_account = payee_account;
	    this.amount = amount;
	  }


	  public String getTransaction_ref() {
		return transaction_ref;
	}

	public void setTransaction_ref(String transaction_ref) {
		this.transaction_ref = transaction_ref;
	}

	public String getValue_date() {
		return value_date;
	}

	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}

	public String getPayer_name() {
		return payer_name;
	}

	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}

	public String getPayer_account() {
		return payer_account;
	}

	public void setPayer_account(String payer_account) {
		this.payer_account = payer_account;
	}

	public String getPayee_name() {
		return payee_name;
	}

	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}

	public String getPayee_account() {
		return payee_account;
	}

	public void setPayee_account(String payee_account) {
		this.payee_account = payee_account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	  public String toString() {
	    return "Transactional_Data [transaction_ref=" + transaction_ref + ", value_date=" + value_date + ", payer_name=" + payer_name + ", payer_account=" + payer_account + ", payee_name=" + payee_name +", payee_account= " + payee_account + ", amount=" + amount + " ]";
	  }
}