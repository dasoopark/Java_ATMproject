package org.kpu.atm.util;

import org.kpu.atm.bank.Account;

public class Statistics {
	
	static public int getMaxAccount(int currentAccountNum, Account[] accountArray) {
		int allPeople = 0;
		int vipMoney = 0;
		for (int i = 0; i < currentAccountNum; i++) {
			allPeople = allPeople + 1;
			if (vipMoney < accountArray[i].getnBalance()) {
				vipMoney = accountArray[i].getnBalance();
			}
		}
		return vipMoney;
	}
	
	
	static public int getATMBalance(int machineBalance, int currentAccountNum, Account[] accountArray) {
		int allMoney = 0;
		for (int i = 0; i < currentAccountNum ; i++) {
			allMoney += accountArray[i].getnBalance();
		}		
		return machineBalance + allMoney;
	}
	
	
	static public int getAllCustomerMoney(int currentAccountNum, Account[] accountArray) {
		int allCustomerMoney = 0;
		for (int i = 0; i < currentAccountNum ; i++) {
			allCustomerMoney += accountArray[i].getnBalance();
		}		
		return allCustomerMoney;
	}
	
	
	static public int getAllCustomerAverageMoney(int currentAccountNum, Account[] accountArray) {
		int allCustomerMoney = 0;
		for (int i = 0; i < currentAccountNum ; i++) {
			allCustomerMoney += accountArray[i].getnBalance();
		}		
		return allCustomerMoney / currentAccountNum;
	}
	
	static public void sortByDesc(int currentAccountNum, Account[] accountArray) {
		for (int i = 0; i < currentAccountNum - 1; i++) {
			for (int j = i + 1; j < currentAccountNum; j++) {
				if (accountArray[i].getnBalance() < accountArray[j].getnBalance()) {
					Account temp = accountArray[j];
					accountArray[j] = accountArray[i];
					accountArray[i] = temp;
				}
			}
		}
	}
	
}