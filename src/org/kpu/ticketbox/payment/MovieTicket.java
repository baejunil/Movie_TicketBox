package org.kpu.ticketbox.payment;

public class MovieTicket {
	public static final char SEAT_EMPTY_MARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	private int nRow; // �¼� ��
	private int nCol; // �¼� ��
	private int nReservedId; // ���� ��ȣ
	private char cStatus; // �¼� ���� - EMPTY, RESERVED, PAY_COMPLETION
	
	public void setSeat(int row, int col) {
		this.nRow = row;
		this.nCol=col;
	} // �¼���ȣ����

	public void setnReservedId(int id) {
		this.nReservedId=id;
	} // �����ȣ����

	public int getnReservedId() {
		return nReservedId;
	} // �����ȣ �б�

	

	public int getnRow() {
		return nRow;
	}

	public int getnCol() {
		return nCol;
	}

	public char getcStatus() {
		return cStatus;
	}

	public void setcStatus(char cStatus) {
		this.cStatus = cStatus;
	}
	
}
