package masai.project.dao;

import java.util.List;

import com.masai.Exception.CustomerException;

import masai.project.Bean.Customer;
import masai.project.Bean.TransactionData;


public interface CusDao {

	public Customer LoginAsCustomer(String username, String password, int accountno)throws CustomerException; 
	
	public int viewCustomerBalance(int cACno) throws CustomerException;
	
	public int Deposit(int cACno, int amount) throws CustomerException; 
	
	public int Withdrawl(int cACno, int amount) throws CustomerException;
	
	public int TransferMoney(int cACno, int amount, int cACno2) throws CustomerException;
	
	public List<TransactionData> viewTransaction(int cACno) throws CustomerException;
}
