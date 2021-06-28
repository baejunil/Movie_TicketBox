package org.kpu.ticketbox.main;

import java.util.*;

import org.kpu.ticketbox.cinema.AnimationScreen;
import org.kpu.ticketbox.cinema.FamillyScreen;
import org.kpu.ticketbox.cinema.PremiumScreen;
import org.kpu.ticketbox.cinema.Screen;
import org.kpu.ticketbox.util.BackupWriter;
import org.kpu.ticketbox.util.Statistics;

public class TicketBox {
	private FamillyScreen famillyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	public static final String ADMIN_PASSWORD = "admin"; // ������

	Scanner scan = new Scanner(System.in);

	public void initScreen() {
		// Screen(��ȭ����, ��ȭ �ٰŸ�, Ƽ�ϰ���, �¼�(rowMax), �¼�(colMax))
		famillyScreen = new FamillyScreen("Ÿ¥", "����� �� �� ���ٸ�, �������� ����!", 8000, 10, 10);
		animationScreen = new AnimationScreen("¯���� ������", "���ġ ¯���� �Ϸ�", 10000, 10, 10);
		premiumScreen = new PremiumScreen("�ϻ�", "1933�� ������ ����� �ô�", 25000, 5, 5);
	}

	public Screen selectScreen() {
		int choice = 0; // �󿵰� ����

		
		Scanner scan = new Scanner(System.in);
		System.out.println("--------------------");
		System.out.println("-   �󿵰� ���� ���θ޴�   -");
		System.out.println("--------------------");
		System.out.println("1. ���� ��ȭ 1��");
		System.out.println("2. �ִϸ��̼� ��ȭ 2��");
		System.out.println("3. �����̾� ��ȭ 3�� (Ŀ��, ���� ����)");
		System.out.println("����(1~3, 9)�� ����");
		System.out.println("9. ������ �޴�");
		System.out.print("�󿵰� ���� : ");
		choice = scan.nextInt();

		switch (choice) {
		case 1:
			return famillyScreen;
		case 2:
			return animationScreen;
		case 3:
			return premiumScreen;
		case 9:
			managerMode();
			System.exit(0);
		default:
			return null;
		}

	}

	void managerMode() {
		
		Scanner scan = new Scanner(System.in);
		String fname = "C:\\temp\\tBoxReceipt.txt";
		String pwd="";
		while (true) {
			System.out.print("��ȣ �Է�  : ");
			pwd=scan.next();
			if(pwd.equals(ADMIN_PASSWORD)==false) {
				System.out.println("��ȣ�� Ʋ�Ƚ��ϴ�. ");
				continue;
			}
			System.out.println("--------------------");
			System.out.println("----  ������ ���   ----");
			System.out.println("--------------------");
			System.out.println("���� ��ȭ�� ���� �Ѿ�: "+ Statistics.sum(famillyScreen.getrmap()));
			System.out.println("�ִϸ��̼� ��ȭ�� ���� �Ѿ�: "+ Statistics.sum(animationScreen.getrmap()));
			System.out.println("�����̾� ��ȭ�� ���� �Ѿ�: "+ Statistics.sum(premiumScreen.getrmap()));
			System.out.println("��ȭ�� �� Ƽ�� �Ǹŷ� : "+(Statistics.ticketsize(famillyScreen.getrmap())+Statistics.ticketsize(animationScreen.getrmap())+Statistics.ticketsize(premiumScreen.getrmap())));
			System.out.println(fname+" ��� �����մϴ�.");
			
			BackupWriter write = new BackupWriter();
			write.backupFile(fname, famillyScreen.getrmap());
			write.backupFile(fname, animationScreen.getrmap());
			write.backupFile(fname, premiumScreen.getrmap());
			System.out.println("���� ��ȭ�� ���� ��� �Ϸ�");
			System.out.println("�ִϸ��̼� ��ȭ�� ���� ��� �Ϸ�");
			System.out.println("�����̾� ��ȭ�� ���� ��� �Ϸ�");
			System.out.print(" �ȳ��� ������ !");
			break;
		}
	}
}
