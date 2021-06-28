package org.kpu.ticketbox.main;

import java.util.*;

import org.kpu.ticketbox.cinema.Screen;

public class TicketBoxMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketBox ticketBox = new TicketBox();
		Scanner scan = new Scanner(System.in);
		Screen screen = null;
		boolean bMainMenu = true; // �󿵰� ���� �޴� ���
		ticketBox.initScreen(); // 3���� ��ũ�� ��ü�� ����
		
		while(true) {
			if(bMainMenu) {
				screen = ticketBox.selectScreen();
				
				if( screen == null ) {
					System.out.print(" �ȳ��� ������ !");
					scan.close();
					System.exit(0);
				}
				bMainMenu = false;
			}
			
			screen.showScreenMenu();
			
			System.out.print(" �޴��� �����ϼ��� >> ");
			try {
				int select = scan.nextInt();
				switch(select) {
				case 1: // ��ũ�� �� ��ȭ ���� ����
						screen.showMovieInfo();
						break;
				case 2: //�¼� ���� ��Ȳ
					screen.showSeatMap();
					break;
				case 3: //�¼� ���� �ϱ�
					screen.reserveTicket();
					break;
				case 4: // �¼� ���� �ϱ�
					screen.changeTicket();
					break;
				case 5: // �¼� ���� �ϱ�
					screen.payment();
					break;
				case 6:
					screen.printTicket(); // Ƽ�� ��� �ϱ�
					break;
				case 7: //���� �޴� �̵�
					screen=ticketBox.selectScreen();
					break;
				}
			}
			catch(InputMismatchException e) {
				System.out.println("��ȣ�� ����� �Է����ּ���.");
			}
		}

	}
}
