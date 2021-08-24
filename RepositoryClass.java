package com.pixeltrice.springbootimportcsvfileapp;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RepositoryClass extends JpaRepository<ModelClass, String>{
}


//@Modifying
//@Query(value = "insert into csvfileapp (transaction_ref, amount, payee_account, payee_name, payer_account, payer_name, value_date)  VALUES (:insertLink,:id)", nativeQuery = true)

//public void logURI(modelclass modelclass);


/* showing ulpad success message when file is successfully written to txt
before writing into .txt validate the data
During validation add two more columns to txt and those two columns are isvalid and feed status*/