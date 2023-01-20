package masai.project.useCase;

import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.AccountException;

import com.masai.Exception.AccountantException;
import com.masai.Exception.CustomerException;

import masai.project.Bean.Accountant;
import masai.project.Bean.Customer;
import masai.project.Bean.TransactionData;


import masai.project.dao.AccDao;
import masai.project.dao.AccDaoImpl;
import masai.project.dao.CusDao;
import masai.project.dao.CusDaoImpl;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		boolean f=true;
		

		while(f) {
			System.out.println();
			System.out.println("Welcome To the Online Banking System");
			System.out.println(); 
			System.out.println("1. Accountant ");
			System.out.println("2. Customer");
			System.out.println();
			System.out.println("Choose your option");
			int choice=sc.nextInt();
			
		switch(choice) {
		case 1:
			System.out.println("========================");
			System.out.println("Enter Accountant Username:");
			String uname=sc.next();
			System.out.println("Enter Accountant Password:");
			String pass=sc.next();
			
			AccDao a=new AccDaoImpl();
			
			try {
				Accountant ab=a.LoginAccountant(uname, pass);
				
				System.out.println("Welcome "+ab.getEname());

				boolean y=true;
				
				while(y) {
					System.out.println("=======================================================\r\n"
							+ "1. Add New Customer Account\r\n"
							+ "2. Edit existing Customer Account\r\n"
							+ "3. Remove the existing Account given by Account no\r\n"
							+ "4. View particular Customer Account details by giving Account no\r\n"
							+ "5. View all the Customer Account details\r\n"
							+ "6. Add new Account for existing Customer\r\n"
							+ "7. View deposit and withdrawal for Customer\r\n"
							+ "8. LOGOUT\r\n"
							+"===========================================================\r\n");
					
					int x=sc.nextInt();
					
					if(x==1) {
						System.out.println("Enter Customer Name: ");
			 			String a2=sc.next();
			 			System.out.println("Enter Customer Email: ");
			 			String a4=sc.next();
			 			System.out.println("Enter CustomerPassword: ");
			 			String a5=sc.next();
			 			System.out.println("Enter Customer Mobile number: ");
			 			String a6=sc.next();
			 			System.out.println("Enter Customer Address: ");
			 			String a7=sc.next();
			 			System.out.println("Enter Amount For Opening Account: ");
			 			int a3=sc.nextInt();
			 			
			 			
			 			int s1=-1;
						try {
							s1 = a.addNewCustomer(a2,a4,a5,a6,a7);
							try {
								a.addAccountCustomer(x, choice);
							} catch (AccountException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					if(x==2) {
						System.out.println("Enter Customer Account No: ");
			 			int u=sc.nextInt();
			 			System.out.println("Enter new Address you want to update: ");
			 			String u2=sc.next();
			 			try {
							String mes=a.updateExistingCustomer(u,u2);
						} catch (CustomerException e) {
							e.printStackTrace();
						}
					}
					
					if(x==3) {
						System.out.println("Enter Customer Account No to remove: ");
						int ac=sc.nextInt();
						String s=null;
						try {
							s=a.deleteAccount(ac);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						if(s!=null)
							System.out.println(s);
					}
					
					if(x==4) {
						System.out.println("Enter Customer Account No to show Customer Details: ");
						String ac=sc.next();
						
						try {
							Customer mes=a.viewCustomerDetails(ac);
							
							if(mes!=null) {
								System.out.println("Details of Customer: ");
								System.out.println("Account No: " + mes.getcACno());
								System.out.println("Name: " + mes.getCname());
								System.out.println("Balance: " + mes.getCbal());
								System.out.println("Email: " + mes.getCmail());
								System.out.println("Password: " + mes.getCpass());
								System.out.println("Mobile: " + mes.getCmob());
								System.out.println("Address: " + mes.getCadd());
								
							}else {
								System.out.println("Account is not store in the database.");

							}
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					if(x==5) {
						try {
							System.out.println("Details of all Customers: ");
							Customer mes=a.viewAllCustomer();
		
							
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(x==6) {
						System.out.println("Enter Existinig Customer Email: ");
						String e=sc.next();
						System.out.println("Enter Existing Customer Mobile number: ");
						String m=sc.next();
						System.out.println("Enter new Account balance for this Customer: ");
						int b=sc.nextInt();
						
						try {
							int c=a.getCustomer(e,m);
							try {
								a.addAccountCustomer(b, c);
							} catch (AccountException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (CustomerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					if(x==7) {
						CusDao cd=new CusDaoImpl();
						System.out.println("Enter Account No. to view Transaction Records");
						int ac=sc.nextInt();
						List<TransactionData> li=null;
						try {
							li=cd.viewTransaction(ac); 
							System.out.println("Account No.: "+li.get(0).getAccountNo());
							li.forEach(v->{
								System.out.println("---------------------------------------------");
								if(v.getDeposit()!=0)
									System.out.println("Amount Credit: "+v.getDeposit());
								if(v.getWithdraw()!=0)
									System.out.println("Amount Debit: "+v.getWithdraw());
								System.out.println("Date and Time: "+ v.getTransaction_time());
							});
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(x==8) {
						System.out.println("Thank You. Visit Again!");
						y=false;
					}
					
				}
			break;
				
			} catch (AccountantException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				
			}
			break;
			
		case 2:
			System.out.println("=====================");
			System.out.println("Enter Customer Username");
			String username=sc.next();
			System.out.println("Enter Customer Password");
			String password=sc.next();
			System.out.println("Enter Customer Account No");
			int acno=sc.nextInt();
			
			CusDao cd=new CusDaoImpl();
			
			try {
				Customer cusb = cd.LoginAsCustomer(username, password,acno);
				System.out.println("Welcome "+cusb.getCname());
				
				boolean m=true;
				
				while(m) {
					System.out.println("=============================\r\n"
							+ "1. View Account Balance\r\n"
							+ "2. Deposit\r\n"
							+ "3. Withdraw\r\n"
							+ "4. Transfer\r\n"
							+ "5. View Transaction History\r\n"
							+ "6. LOGOUT\r\n"
							+"==============================\r\n");

					
					int x=sc.nextInt();
					
					if(x==1) {
						System.out.println("Show Current Account Balance: ");
						System.out.println(cd.viewCustomerBalance(acno)); 
					}
					if(x==2) {
						System.out.println("Enter Amount to Deposit: ");
						int am=sc.nextInt();
						cd.Deposit(acno, am);
						System.out.println("After Depoit Your Balance is: ");
						System.out.println(cd.viewCustomerBalance(acno));
					}
					
					if(x==3) {
						System.out.println("Enter Amount you want to Withdrawl: ");
						int wa=sc.nextInt();
						try {
							cd.Withdrawl(acno, wa);
							System.out.println("After Withdrawl Your Balance is: ");
							System.out.println(cd.viewCustomerBalance(acno));
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
					}
					
					if(x==4) {

						System.out.println("Enter Amount which you Transfer: ");
						int t=sc.nextInt();
						System.out.println("Enter Account No. to transfer amount: ");
						int ac=sc.nextInt();
						
						try {
							cd.TransferMoney(acno, t, acno);
							System.out.println("Amount Transferred Succesfully!");
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
					}
					
					if(x==5) {
						List<TransactionData> li=null;
						try {
							li= cd.viewTransaction(acno);
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
						System.out.println("Account No.: " + li.get(0).getAccountNo());
						
						li.forEach(v->{
							System.out.println("----------------------------------------------------");
							if(v.getDeposit()!=0)
								System.out.println("Amount Credit: "+ v.getDeposit());
							if(v.getWithdraw()!=0)
								System.out.println("Amount Debit : "+ v.getWithdraw());
							System.out.println("Date and Time: "+ v.getTransaction_time());
						});
						
					}
					if(x==6) {
						System.out.println("Customer Logged out. Thank You!");
						m=false;
					}
					
					
				}
				break;
				
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			break;
			
			
			
		}
		
		}
		
		
	}

}