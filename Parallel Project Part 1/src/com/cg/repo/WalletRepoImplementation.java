package com.cg.repo;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cg.beans.Customer;
import com.cg.beans.Transaction;

public class WalletRepoImplementation implements WalletRepo {

	
	private Map<String, Customer> customers = new HashMap<String, Customer>();
	
	
	/* (non-Javadoc)
	 * @see com.cg.repo.WalletRepo#save(com.cg.beans.Customer)
	 */
	@Override
	public boolean save(Customer customer) {
		
		
		
		if(customers.containsKey(customer.getMobileNumber())) {
			
			return false;
		}
		
		else {
			customers.put(customer.getMobileNumber(), customer);
			return true;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cg.repo.WalletRepo#search(java.lang.String)
	 */
	@Override
	public Customer search(String mobileNumber) {
		
		if(customers.containsKey(mobileNumber)) {
			
			return customers.get(mobileNumber);
			
		}
		
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.cg.repo.WalletRepo#saveTransactionDetails(java.lang.String, com.cg.beans.Transaction)
	 */
	@Override
	public boolean saveTransactionDetails(String MobileNumber, Transaction transaction) {
		
		transaction.settAmount(customers.get(MobileNumber).getWallet().getBalance());
		
		LinkedList<Transaction> list = new LinkedList<>();
		
		list.addAll(customers.get(MobileNumber).getTransaction());
		list.add(transaction);
		customers.get(MobileNumber).setTransaction(list);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.cg.repo.WalletRepo#getLast10Transaction(java.lang.String)
	 */
	@Override
	public List<Transaction> getLast10Transaction(String mobileNumber){
		if(customers.containsKey(mobileNumber)) {
			LinkedList<Transaction> list = new LinkedList<>();
			list.addAll(customers.get(mobileNumber).getTransaction());
			List<Transaction> res = new LinkedList<>();
			Collections.reverse(list);
			for(int i=0;i<10 && i<list.size();i++) {
				res.add(list.get(i));
			}
			return res;
		}
		else {
			return null;
		}
		
	}
	
}
