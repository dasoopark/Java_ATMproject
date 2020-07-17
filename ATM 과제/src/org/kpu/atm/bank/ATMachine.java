package org.kpu.atm.bank;

import java.util.Scanner;
import org.kpu.atm.util.Statistics;

public class ATMachine {
	private Account[] accountArray; // �����¹迭 ��������
	private static int nMachineBalance; // ATM�ܰ�
	private int nMaxAccountNum; // ������ �������� �迭ũ��
	private static int nCurrentAccountNum; // �輳�� ������ ��
	private String strManagerPassword; // ������ ��й�ȣ
	private String SetPassword; // ����� ���� ��й�ȣ
	Scanner scanner = new Scanner(System.in);

	public static final int BASE_ACCOUNT_ID = 100; // ������ �߱޽� ���� ��ȣ

	public ATMachine(int size, int balance, String password) { // ������
		this.nMaxAccountNum = size; // 1000
		this.nMachineBalance = balance; // 50����
		this.strManagerPassword = password; // ������ ��й�ȣ("admin")
		accountArray = new Account[nMaxAccountNum];
		this.nCurrentAccountNum = 0;
	}

	public void createAccount() { // ���� ����
		System.out.println("----------����---------");
		System.out.print("�̸� �Է�: ");
		String Setname = scanner.nextLine();
		System.out.print("��ȣ �Է�: ");
		String SetPassword = scanner.nextLine();
		accountArray[nCurrentAccountNum] = new Account(BASE_ACCOUNT_ID + nCurrentAccountNum, 0, Setname, SetPassword);
		accountArray[nCurrentAccountNum].getnID();
		accountArray[nCurrentAccountNum].getnBalance();
		System.out.println(Setname + "��" + " " + (BASE_ACCOUNT_ID + nCurrentAccountNum) + "��" + " "
				+ "���¹�ȣ�� ���������� �����Ǿ����ϴ�. �����մϴ�");
		nCurrentAccountNum++;
	}

	public void checkMoney() { // ���� ��ȸ
		if (ErrorAlarm())
			return;
		System.out.println("----------��ȸ---------");
		System.out.print("���¹�ȣ �Է�:");
		int AccNum = scanner.nextInt();
		scanner.nextLine(); // int ��ĳ�ʿ� Line ��ĳ�ʰ� ��ĥ�� �Է¹��� �� ���� ���๮�ڸ� ����� ����
		System.out.print("��й�ȣ �Է�:");
		SetPassword = scanner.nextLine();
		
		for (int i = 0; i < nCurrentAccountNum ; i++) {
			if (accountArray[i].getnID() == AccNum) {
				accountArray[i].authenticate(AccNum, SetPassword);
				if(!accountArray[i].authenticate(AccNum, SetPassword))
						return;
			}
		}
		
		for (int i = 0; i < nMaxAccountNum; i++) {
			if (accountArray[i].getnID() == AccNum) {
				System.out.println("���� �ܾ�:" + " " + accountArray[i].getnBalance());
				break;
			}
		}
	}
	
	

	boolean ErrorAlarm() {
		if (nCurrentAccountNum == 0) {
			System.out.println("���� ������ �ϳ��� �ؾ� ������ �� �ִ� �޴��Դϴ�.");
			return true;
		}
		return false;
	}

	public void displayMenu() { // ���� �޴� ǥ��
		System.out.println("----------------------");
		System.out.println("-      KPU BANK      -");
		System.out.println("----------------------");
		System.out.println("1. ���� ����");
		System.out.println("2. ���� ��ȸ");
		System.out.println("3. ���� �Ա�");
		System.out.println("4. ���� ���");
		System.out.println("5. �� ����");
		System.out.println("9. ���� ����");
	}

	public void depositMoney() { // ���� �Ա�
		if (ErrorAlarm())
			return;
		System.out.println("----------�Ա�---------");
		System.out.print("���¹�ȣ �Է�:");
		int AccNum = scanner.nextInt();
		scanner.nextLine(); // int ��ĳ�ʿ� Line ��ĳ�ʰ� ��ĥ�� �Է¹��� �� ���� ���๮�ڸ� ����� ����
		System.out.print("��й�ȣ �Է�:");
		String InputPassword = scanner.nextLine();
		
		for (int i = 0; i < nCurrentAccountNum ; i++) {
			if (accountArray[i].getnID() == AccNum) {
				accountArray[i].authenticate(AccNum, InputPassword);
				if(!accountArray[i].authenticate(AccNum,InputPassword))
						return;
			}
		}
		
		System.out.print("�Ա�  ��   �Է�:");
		int DepoMoney = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < nMaxAccountNum; i++) {
			if (accountArray[i].getnID() == AccNum) {
				accountArray[i].deposit(DepoMoney);
				System.out.println("�Ա�  ��  �ܾ�: "+accountArray[i].getnBalance());
				break;
			}
		}
	}

	public void widrawMoney() { // ���� ���
		if (ErrorAlarm())
			return;
		System.out.println("----------���---------");
		System.out.print("���¹�ȣ �Է�:");
		int AccNum = scanner.nextInt();
		scanner.nextLine(); // int ��ĳ�ʿ� Line ��ĳ�ʰ� ��ĥ�� �Է¹��� �� ���� ���๮�ڸ� ����� ����
		System.out.print("��й�ȣ �Է�:");
		String InputPassword = scanner.nextLine();
		
		for (int i = 0; i < nCurrentAccountNum ; i++) {
			if (accountArray[i].getnID() == AccNum) {
				accountArray[i].authenticate(AccNum, InputPassword);
				if(!accountArray[i].authenticate(AccNum,InputPassword))
						return;
			}
		}

		// ��ġ ��ȿ�� �˻�

		System.out.print("���  ��   �Է�:");
		if (ErrorAlarm())
			return;
		int WidrawMoney = scanner.nextInt();
		scanner.nextLine();

		for (int i = 0; i < nMaxAccountNum; i++) {
			if (accountArray[i].getnID() == AccNum) {
				if (accountArray[i].getnBalance() <= 0) {
					System.out.println("�ðܳ��� ���� ���, ����� �Ұ����մϴ�.");
					return;
				}
				accountArray[i].widraw(WidrawMoney);
				System.out.println("��� ��  �ܾ�: " + accountArray[i].getnBalance());
				break;
			}
		}
	}

	public void managerMode() {

		if (nCurrentAccountNum == 0) {
			System.out.println("���� ������ �ϳ��� �ؾ� ������ �� �ִ� �޴��Դϴ�.");
			return;
		}

		System.out.println("---------������--------");
		System.out.print("������ ��й�ȣ �Է�:");
		String InputPassword = scanner.nextLine();

		int maxAccount = Statistics.getMaxAccount(nCurrentAccountNum, accountArray);

		if (strManagerPassword.equals(InputPassword)) {
			
			System.out.println("ATM ����  �ܰ�:" + "      "+ Statistics.getATMBalance(nMachineBalance, nCurrentAccountNum, accountArray));
			System.out.println("��   �ܰ�  �Ѿ�:" + "      "+ Statistics.getAllCustomerMoney(nCurrentAccountNum, accountArray) + "("+ nCurrentAccountNum + "��)");
			System.out.println("��   �ܰ�  ���:" + "      "+ Statistics.getAllCustomerAverageMoney(nCurrentAccountNum, accountArray));
			System.out.println("��   �ܰ�  �ְ�:" + "      " + maxAccount);
			System.out.println("��   ����  ��Ȳ:(�� �ܰ� ���� ���� ����)");

			Statistics.sortByDesc(nCurrentAccountNum, accountArray);

			for (int i = 0; i < nCurrentAccountNum; i++) {
				System.out.println(accountArray[i].getAccountName() + "       " + accountArray[i].getnID() + "       "
						+ accountArray[i].getnBalance() + "��");
			}
		} // if
		else {
			System.out.println("�߸��� ��ȣ �Դϴ�.");
		}
	}


} // ��ü