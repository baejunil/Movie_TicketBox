package org.kpu.ticketbox.cinema;

import java.util.Scanner;

import org.kpu.ticketbox.payment.BankTransfer;
import org.kpu.ticketbox.payment.CardPay;
import org.kpu.ticketbox.payment.MobilePay;
import org.kpu.ticketbox.payment.MovieTicket;
import org.kpu.ticketbox.payment.Pay;
import org.kpu.ticketbox.payment.Receipt;

public class AnimationScreen extends Screen {

	public AnimationScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				seatArray[i][j] = new MovieTicket();
				seatArray[i][j].setcStatus(MovieTicket.SEAT_EMPTY_MARK); // -�� �ʱ�ȭ
			}
		}
	}

	public void showScreenMenu() {
		System.out.println("--------------------");
		System.out.println("��ȭ �޴� - " + strMovieName);
		System.out.println("--------------------");
		System.out.println("1. �� ��ȭ ����");
		System.out.println("2. �¼� ���� ��Ȳ");
		System.out.println("3. �¼� ���� �ϱ�");
		System.out.println("4. �¼� ���� �ϱ�");
		System.out.println("5. �¼� ���� �ϱ�");
		System.out.println("6. Ƽ�� ��� �ϱ�");
		System.out.println("7. ���� �޴� �̵�");
	}

	public void showMovieInfo() {
		System.out.println(strMovieName + " �Ұ�");
		System.out.println("��ȭ�� : �ִϸ��̼� ��ȭ 2��");
		System.out.println("�ٰŸ� : " + strMovieStory);
		System.out.println("���� : " + nTicketPrice);
	}

	public void showSeatMap() { // �󿵰� �¼� ���� ��Ȳ �����ֱ�
		System.out.println("      [ �¼� ���� ��Ȳ ]");
		System.out.println("        [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]");
		for (int i = 0; i < nRowMax; i++) {
			System.out.print("[" + (i + 1) + "]	");
			for (int j = 0; j < nColMax; j++) {
				System.out.print("[" + seatArray[i][j].getcStatus() + "] ");
			}
			System.out.println("");
		}
	}

	public void reserveTicket() { // �¼� ����
		System.out.println("[ �¼� ���� ]");

		Scanner scan = new Scanner(System.in);
		int rownum, colnum;
		while (true) {
			System.out.print("�¼� �� ��ȣ �Է�(1~10) : ");
			rownum = scan.nextInt();
			System.out.print("�¼� �� ��ȣ �Է�(1~10) : ");
			colnum = scan.nextInt();
			if (rownum > 10 || colnum > 10) {
				System.out.println("�¼���ȣ�� �ٽ� Ȯ���ϼ���.");
				break;
			} else if (rownum < 0 || colnum < 0) {
				System.out.println("�¼���ȣ�� �ٽ� Ȯ���ϼ���.");
				break;
			} else if (seatArray[rownum - 1][colnum - 1].getcStatus() != MovieTicket.SEAT_EMPTY_MARK) {
				System.out.println("����� �¼��Դϴ�.");
				break;
			}

			else {
				System.out.println("��[" + rownum + "] ��[" + colnum + "] " + getnCurrentReservedId() + " �����ȣ�� �����Ǿ����ϴ�.");
				seatArray[rownum - 1][colnum - 1].setcStatus(MovieTicket.SEAT_RESERVED_MARK); // R�� ����
				seatArray[rownum - 1][colnum - 1].setnReservedId(getnCurrentReservedId()); // �����ȣ ����
				seatArray[rownum - 1][colnum - 1].setSeat(rownum, colnum); // �����ȣ�� �´� �� �� ����
				setnCurrentReservedId(getnCurrentReservedId() + 1); // �����ȣ ++

				break;
			}
		}

	}

	public void changeTicket() { //
		int rownum, colnum, seatrv; // ������ �¼� ��, ������ �¼� ��, �����ȣ
		int oldrow, oldcol; // ���� ��, ��

		Scanner scan = new Scanner(System.in);
		System.out.println(" [ �¼� ����] ");
		while (true) {

			System.out.print("�¼� ���� ��ȣ �Է� : ");
			seatrv = scan.nextInt();
			MovieTicket existrv = checkReservedId(seatrv); // �����ȣ �޾ƿ���
			System.out.print("�¼� �� ��ȣ �Է�(1~10) : ");
			rownum = scan.nextInt();
			System.out.print("�¼� �� ��ȣ �Է�(1~10) : ");
			colnum = scan.nextInt();
			if (rownum > 10 || colnum > 10) {
				System.out.println("�¼���ȣ�� �ٽ� Ȯ���ϼ���.");
				break;
			} else if (rownum < 0 || colnum < 0) {
				System.out.println("�¼���ȣ�� �ٽ� Ȯ���ϼ���.");
				break;
			}

			else if (seatArray[rownum][colnum].getcStatus() != MovieTicket.SEAT_EMPTY_MARK) {
				System.out.println("����� �¼��Դϴ�.");
				break;
			}

			else if (existrv == null) {
				System.out.println("�������� �ʴ� �����ȣ�Դϴ�.");
				break;
			} else {
				oldrow = existrv.getnRow(); // ���� �� �޾ƿ���
				oldcol = existrv.getnCol(); // ���� �� �޾ƿ���

				System.out.println(
						"�����ȣ " + existrv.getnReservedId() + " ��[" + rownum + "] ��[" + colnum + "] " + "�¼����� ����Ǿ����ϴ�.");

				seatArray[oldrow - 1][oldcol - 1].setcStatus(MovieTicket.SEAT_EMPTY_MARK);// ���� ��,�� ���ڸ�
				seatArray[oldrow - 1][oldcol - 1].setnReservedId(0); // ���� �ڸ� �����ȣ �ʱ�ȭ
				seatArray[rownum - 1][colnum - 1].setnReservedId(seatrv); // �����ȣ �缳��
				seatArray[oldrow][oldcol].setSeat(rownum, colnum); // �����ȣ�� �´� �� �� ����
				seatArray[rownum - 1][colnum - 1].setcStatus(MovieTicket.SEAT_RESERVED_MARK); // ����� �ڸ� R

				break;
			}
		}

	}

	private MovieTicket checkReservedId(int id) {
		for (int i = 0; i < nRowMax; i++) {
			for (int j = 0; j < nColMax; j++) {
				if (seatArray[i][j].getnReservedId() == id)
					return seatArray[i][j];
			}
		}
		return null;
	}

	public void payment() {
		int seatrv, way;

		Scanner scan = new Scanner(System.in);
		System.out.println("[�¼� ���� ����]");
		while (true) {
			System.out.print("���� ��ȣ �Է� : ");
			seatrv = scan.nextInt();

			MovieTicket existrv = checkReservedId(seatrv); // �����ȣ �޾ƿ���

			if (existrv == null) {
				System.out.println("�������� �ʴ� �����ȣ�Դϴ�.");
				break;
			}

			if (existrv.getcStatus() != MovieTicket.SEAT_RESERVED_MARK) {
				System.out.println("�������� �ʰų� ������ �����ȣ �Դϴ�.");
				break;
			}

			System.out.println("--------------------");
			System.out.println(" ����  ���  ����  ");
			System.out.println("--------------------");
			System.out.println("1. ���� ��ü");
			System.out.println("2. ī�� ����");
			System.out.println("3. ����� ����");
			System.out.print("���� ��� �Է�(1~3) : ");

			way = scan.nextInt();

			if (way == Pay.BANK_TRANSFER_PAYMENT) { // ���� ��ü
				String bname, bnumber;
				System.out.println("[ ������ü ]");
				BankTransfer b = new BankTransfer();
				System.out.print("�̸� �Է� : ");
				bname = scan.next();
				System.out.print("���� ��ȣ �Է�(12�ڸ� ��) : ");
				bnumber = scan.next();
				if (bnumber.length() != 12) {
					System.out.println("�߸��� ���� ��ȣ �Դϴ�.");
					break;
				}
				System.out.println(
						"���� ������ �Ϸ� �Ǿ����ϴ�. : " + nTicketPrice * (1 + BankTransfer.BANK_TRANSFER_COMMISION) + "��");
				Receipt br = b.charge(strMovieName, nTicketPrice, bname, bnumber); // ���� ����
				setReceiptMap(seatrv, br); // �����ȣ, ������ ����
				existrv.setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // �����Ϸ� ǥ�� ����
				break;

			} else if (way == Pay.CREDIT_CARD_PAYMENT) { // ī�����
				String cname, cnumber;
				System.out.println("[ ī����� ]");
				CardPay c = new CardPay();
				System.out.print("�̸� �Է� : ");
				cname = scan.next();
				System.out.print("ī�� ��ȣ �Է�(12�ڸ� ��) : ");
				cnumber = scan.next();
				if (cnumber.length() != 12) {
					System.out.println("�߸��� ī�� ��ȣ �Դϴ�.");
					break;
				}
				System.out.println("ī�� ������ �Ϸ� �Ǿ����ϴ�. : " + nTicketPrice * (1 + CardPay.CARD_COMMISION) + "��");
				Receipt cr = c.charge(strMovieName, nTicketPrice, cname, cnumber);
				setReceiptMap(seatrv, cr);
				existrv.setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // �����Ϸ� ǥ�� ����
				break;
			} else if (way == Pay.MOBILE_PHONE_PAYMENT) { // ����� ����
				String mname, mnumber;
				System.out.println("[ ����ϰ��� ]");
				MobilePay m = new MobilePay();
				System.out.print("�̸� �Է� : ");
				mname = scan.next();
				System.out.print("�ڵ��� ��ȣ �Է�(11�ڸ� ��) : ");
				mnumber = scan.next();
				if (mnumber.length() != 11) {
					System.out.println("�߸��� �ڵ��� ��ȣ �Դϴ�.");
					break;
				}
				System.out.println("����� ������ �Ϸ� �Ǿ����ϴ�. : " + nTicketPrice * (1 + MobilePay.MOBILE_COMMISION) + "��");
				Receipt mr = m.charge(strMovieName, nTicketPrice, mname, mnumber);
				setReceiptMap(seatrv, mr);
				existrv.setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // �����Ϸ� ǥ�� ����
				break;
			} else {
				System.out.println("���� ���� ��� �Դϴ�.");
				break;
			}
		}
	}

	public void printTicket() { // �¼� Ƽ�� ���
		System.out.println("[�¼� Ƽ�� ���]");

		Scanner scan = new Scanner(System.in);
		while (true) {
			int seatrv;
			System.out.print("�¼� ���� ��ȣ �Է� : ");
			seatrv = scan.nextInt();
			MovieTicket existrv = checkReservedId(seatrv);
			if (existrv == null) {
				System.out.println("�������� �ʴ� �����ȣ�Դϴ�.");
				break;
			} else if (existrv.getcStatus() != MovieTicket.SEAT_PAY_COMPLETION_MARK) {
				System.out.println("���� ���� ���� �¼��Դϴ�.");
				break;
			}
			System.out.println("--------------------");
			System.out.println(" --   Receipt   -- ");
			System.out.println("--------------------");

			Receipt i = getReceiptIdMap(seatrv);
			System.out.println(i.toString());
			break;
		}

	}

}
