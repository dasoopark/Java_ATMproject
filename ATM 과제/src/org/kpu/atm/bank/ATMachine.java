package org.kpu.atm.bank;

import java.util.Scanner;
import org.kpu.atm.util.Statistics;

public class ATMachine {
	private Account[] accountArray; // 고객계좌배열 참조변수
	private static int nMachineBalance; // ATM잔고
	private int nMaxAccountNum; // 고객계좌 참조변수 배열크기
	private static int nCurrentAccountNum; // 계설된 고객계좌 수
	private String strManagerPassword; // 관리자 비밀번호
	private String SetPassword; // 사용자 계좌 비밀번호
	Scanner scanner = new Scanner(System.in);

	public static final int BASE_ACCOUNT_ID = 100; // 고객계좌 발급시 시작 번호

	public ATMachine(int size, int balance, String password) { // 생성자
		this.nMaxAccountNum = size; // 1000
		this.nMachineBalance = balance; // 50만원
		this.strManagerPassword = password; // 관리자 비밀번호("admin")
		accountArray = new Account[nMaxAccountNum];
		this.nCurrentAccountNum = 0;
	}

	public void createAccount() { // 계좌 개설
		System.out.println("----------개설---------");
		System.out.print("이름 입력: ");
		String Setname = scanner.nextLine();
		System.out.print("암호 입력: ");
		String SetPassword = scanner.nextLine();
		accountArray[nCurrentAccountNum] = new Account(BASE_ACCOUNT_ID + nCurrentAccountNum, 0, Setname, SetPassword);
		accountArray[nCurrentAccountNum].getnID();
		accountArray[nCurrentAccountNum].getnBalance();
		System.out.println(Setname + "님" + " " + (BASE_ACCOUNT_ID + nCurrentAccountNum) + "번" + " "
				+ "계좌번호가 정상적으로 개설되었습니다. 감사합니다");
		nCurrentAccountNum++;
	}

	public void checkMoney() { // 계좌 조회
		if (ErrorAlarm())
			return;
		System.out.println("----------조회---------");
		System.out.print("계좌번호 입력:");
		int AccNum = scanner.nextInt();
		scanner.nextLine(); // int 스캐너와 Line 스캐너가 겹칠때 입력버퍼 에 남은 개행문자를 지우기 위함
		System.out.print("비밀번호 입력:");
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
				System.out.println("계좌 잔액:" + " " + accountArray[i].getnBalance());
				break;
			}
		}
	}
	
	

	boolean ErrorAlarm() {
		if (nCurrentAccountNum == 0) {
			System.out.println("계좌 개설을 하나라도 해야 실행할 수 있는 메뉴입니다.");
			return true;
		}
		return false;
	}

	public void displayMenu() { // 메인 메뉴 표시
		System.out.println("----------------------");
		System.out.println("-      KPU BANK      -");
		System.out.println("----------------------");
		System.out.println("1. 계좌 개설");
		System.out.println("2. 계좌 조회");
		System.out.println("3. 계좌 입금");
		System.out.println("4. 계좌 출금");
		System.out.println("5. 고객 관리");
		System.out.println("9. 업무 종료");
	}

	public void depositMoney() { // 게좌 입금
		if (ErrorAlarm())
			return;
		System.out.println("----------입금---------");
		System.out.print("계좌번호 입력:");
		int AccNum = scanner.nextInt();
		scanner.nextLine(); // int 스캐너와 Line 스캐너가 겹칠때 입력버퍼 에 남은 개행문자를 지우기 위함
		System.out.print("비밀번호 입력:");
		String InputPassword = scanner.nextLine();
		
		for (int i = 0; i < nCurrentAccountNum ; i++) {
			if (accountArray[i].getnID() == AccNum) {
				accountArray[i].authenticate(AccNum, InputPassword);
				if(!accountArray[i].authenticate(AccNum,InputPassword))
						return;
			}
		}
		
		System.out.print("입금  액   입력:");
		int DepoMoney = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < nMaxAccountNum; i++) {
			if (accountArray[i].getnID() == AccNum) {
				accountArray[i].deposit(DepoMoney);
				System.out.println("입금  후  잔액: "+accountArray[i].getnBalance());
				break;
			}
		}
	}

	public void widrawMoney() { // 계좌 출금
		if (ErrorAlarm())
			return;
		System.out.println("----------출금---------");
		System.out.print("계좌번호 입력:");
		int AccNum = scanner.nextInt();
		scanner.nextLine(); // int 스캐너와 Line 스캐너가 겹칠때 입력버퍼 에 남은 개행문자를 지우기 위함
		System.out.print("비밀번호 입력:");
		String InputPassword = scanner.nextLine();
		
		for (int i = 0; i < nCurrentAccountNum ; i++) {
			if (accountArray[i].getnID() == AccNum) {
				accountArray[i].authenticate(AccNum, InputPassword);
				if(!accountArray[i].authenticate(AccNum,InputPassword))
						return;
			}
		}

		// 일치 유효성 검사

		System.out.print("출금  액   입력:");
		if (ErrorAlarm())
			return;
		int WidrawMoney = scanner.nextInt();
		scanner.nextLine();

		for (int i = 0; i < nMaxAccountNum; i++) {
			if (accountArray[i].getnID() == AccNum) {
				if (accountArray[i].getnBalance() <= 0) {
					System.out.println("맡겨놓은 돈이 없어서, 출금이 불가능합니다.");
					return;
				}
				accountArray[i].widraw(WidrawMoney);
				System.out.println("출금 후  잔액: " + accountArray[i].getnBalance());
				break;
			}
		}
	}

	public void managerMode() {

		if (nCurrentAccountNum == 0) {
			System.out.println("계좌 개설을 하나라도 해야 실행할 수 있는 메뉴입니다.");
			return;
		}

		System.out.println("---------고객관리--------");
		System.out.print("관리자 비밀번호 입력:");
		String InputPassword = scanner.nextLine();

		int maxAccount = Statistics.getMaxAccount(nCurrentAccountNum, accountArray);

		if (strManagerPassword.equals(InputPassword)) {
			
			System.out.println("ATM 현금  잔고:" + "      "+ Statistics.getATMBalance(nMachineBalance, nCurrentAccountNum, accountArray));
			System.out.println("고객   잔고  총액:" + "      "+ Statistics.getAllCustomerMoney(nCurrentAccountNum, accountArray) + "("+ nCurrentAccountNum + "명)");
			System.out.println("고객   잔고  평균:" + "      "+ Statistics.getAllCustomerAverageMoney(nCurrentAccountNum, accountArray));
			System.out.println("고객   잔고  최고:" + "      " + maxAccount);
			System.out.println("고객   계좌  현황:(고객 잔고 내림 차순 정렬)");

			Statistics.sortByDesc(nCurrentAccountNum, accountArray);

			for (int i = 0; i < nCurrentAccountNum; i++) {
				System.out.println(accountArray[i].getAccountName() + "       " + accountArray[i].getnID() + "       "
						+ accountArray[i].getnBalance() + "원");
			}
		} // if
		else {
			System.out.println("잘못된 번호 입니다.");
		}
	}


} // 전체