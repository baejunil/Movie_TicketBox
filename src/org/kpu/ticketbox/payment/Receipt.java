package org.kpu.ticketbox.payment;

public class Receipt {
	String client; // ����� �̸�
	String productName; // ��ȭ ��ǰ �̸�
	String payMethod; // ���� ����
	String payNumber; // ���� ����(��ȣ)
	double subTotalAmount; // Ŀ�̼� ������ �ݾ�
	double totalAmount; // Ŀ�̼� ������ �ݾ�
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public Receipt (String client,String productName,String payMethod,String payNumber,double subTotalAmount,double totalAmount)
	{
		this.client=client;
		this.productName=productName;
		this.payMethod=payMethod;
		this.payNumber=payNumber;
		this.subTotalAmount=subTotalAmount;
		this.totalAmount=totalAmount;
	}
	
	public String toString() 
	{
		return "[ Client :	"+client+"	]"+"\n" + "[ Product :	"+productName+"	]"+"\n" + "[ PayMethod :	"+payMethod+"	]"+"\n"+ "[ PayNumber :	"+payNumber+"	]"+"\n" + "[ SubTotal :	"+subTotalAmount+"	]"+"\n" + "[ Total :	"+String.format("%.1f", totalAmount)+"	]"+"\n";
	}
	
	public String toBackupString() {
		 return this.client+","+this.productName+","+this.payMethod+","+this.payNumber+","+this.subTotalAmount+","+this.totalAmount;
	}

	
}
