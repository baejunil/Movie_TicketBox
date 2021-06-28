package org.kpu.ticketbox.cinema;


import java.util.HashMap;

import org.kpu.ticketbox.payment.MovieTicket;
import org.kpu.ticketbox.payment.Receipt;

public abstract class Screen {
	protected int nTicketPrice; // Ƽ�� ����
	protected int nRowMax; // �¼� �� �ִ� ��
	protected int nColMax; // �¼� �� �ִ� ��
	protected String strMovieName; // ������ ��ȭ ����
	protected String strMovieStory; // ������ ��ȭ �ٰŸ�
	protected MovieTicket[][] seatArray; // �¼� 2���� �迭
	private int nCurrentReservedId = 100; // �����ȣ
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>();
	
	public Receipt getReceiptIdMap(int id) { //�����ȣ ���
		return receiptMap.get(id);
	}

	public void setReceiptMap(int id, Receipt receipt) { // �����ȣ, receipt ����
		receiptMap.put(id, receipt);
	}
	
	public HashMap<Integer,Receipt> getrmap()
	{
		return receiptMap;
	}

	public int getnCurrentReservedId() {
		return nCurrentReservedId;
	}

	public void setnCurrentReservedId(int nCurrentReservedId) {
		this.nCurrentReservedId = nCurrentReservedId;
	}

	public abstract void showMovieInfo(); // ��ȭ ���� �����ֱ�

	Screen(String name, String story, int price, int row, int col) { // ������
		this.strMovieName=name;
		this.strMovieStory=story;
		this.nTicketPrice=price;
		this.nRowMax=row;
		this.nColMax=col;
		this.seatArray = new MovieTicket [row][col];
	}

	public void showScreenMenu() { // �󿵰� �޴� �����ֱ�
		
	}

	public void showSeatMap() { // �󿵰� �¼� ���� ��Ȳ �����ֱ�
	}
	
	public void reserveTicket() { // �¼� ����	
	}
	
	public void changeTicket() {} //�¼� ����
	
	
	private MovieTicket checkReservedId(int id) {return null;} //�����ȣ Ȯ��
	
	public void payment(){} // �¼� ���� ����
	
	public void printTicket(){} // �¼� Ƽ�� ���

	
	
}
