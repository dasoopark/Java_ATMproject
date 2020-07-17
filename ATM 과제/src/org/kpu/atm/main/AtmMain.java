package org.kpu.atm.main;
import org.kpu.atm.bank.ATMachine;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AtmMain{
	public static void main(String[] args){
		//�ʱ� ���� - ���������� �ִ���¼� (1000����),
		//ATM�ܱ�(50����), ������ ��ȣ
	
		ATMachine atm = new ATMachine(1000,500000, "admin");
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			atm.displayMenu();
			System.out.print("�޴��� �����ϼ��� >> ");
			try {
				int select = scan.nextInt();
				switch (select) {
				case 1: // ���� ����
					atm.createAccount();
					break;
				case 2: // ���� ��ȸ
					atm.checkMoney();
					break;
				case 3: // ���� �Ա�
					atm.depositMoney();
					break;
				case 4: // ���� ���
					atm.widrawMoney();
					break;
				case 5: // �� ����
					atm.managerMode();
					break;
				case 9: // ���� ����
					System.out.println("�ȳ��� ������ !");
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println("��Ȯ�ϰ�   �Է����ּ���");
				scan.nextLine();
				continue;
			} 
		}
	}
}