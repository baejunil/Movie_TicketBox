package org.kpu.ticketbox.payment;

public class MobilePay {
	public static final double MOBILE_COMMISION = 0.2;

	public Receipt charge(String product, double amount, String name, String number) {
		Receipt receipt = new Receipt(name, product, "MobilePay", number, amount, amount * (1 + MOBILE_COMMISION));
		//�̸�, ��ȭ ����, �������, �����ȣ, ��������, Ŀ�̼Ǻ��� ����
		return receipt;
	}
}
