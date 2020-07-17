package org.kpu.atm.bank;

public class Account {
	private int nID; // ���� ��ȣ
	private int nBalance; // ���� �ܰ�
	private String strAccountName; // ����
	private String strPassword; // ���� ��й�ȣ

	public Account(int id, int money, String name, String password) { // ������ }
		this.nID = id;
		this.nBalance = money;
		this.strAccountName = name;
		this.strPassword = password;
	}

	 public boolean authenticate(int id, String passwd){ //���� Ȯ��
		 if (nID == id && passwd.equals(strPassword)) {
				System.out.println("��ġ�� ��й�ȣ�� �Է��Ͽ����ϴ�.");
				return true;
			}
		 else {
			System.out.println("��ġ���� ���� ��й�ȣ�� �Է��Ͽ����ϴ�.");
			return false;
		 }
	 }
	 
	public int deposit(int money) {
		return this.nBalance += money;
	}

	public int widraw(int money) {
		return this.nBalance -= money;
	}

	public int getnID() {
		return nID;
	}

	public int getnBalance() {
		return nBalance;
	}
	
	public String getAccountName() {
		return strAccountName;
	}
}
