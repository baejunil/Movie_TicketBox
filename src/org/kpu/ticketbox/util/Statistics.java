package org.kpu.ticketbox.util;

import java.util.HashMap;

import org.kpu.ticketbox.payment.Receipt;

public class Statistics {
	// ��ũ�� �� ���� �ݾ� �Ѿ� ���
	public static double sum( HashMap<Integer, Receipt> map) {
		double tsum=0;
		for(int i =0;i<map.size();i++) {
			Receipt r = map.get(i+100);
			tsum+=r.getTotalAmount();
		}
		return tsum;
	}
	
	public static int ticketsize(HashMap<Integer, Receipt> map) {
		return map.size();
	}

}
