package com.mtit.latefeenotifierproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LatefeeProducerImpl implements LatefeeProducer {
	
	private Map<Integer, Latefee> latefeerecords = new HashMap<>(); 
	private int nextlatefeeRecordId = 1;
	
	
	@Override
	public void addLatefeeRecord(Latefee latefee) {
		latefee.setMemberID(nextlatefeeRecordId++);
		latefeerecords.put(latefee.getMemberID(), latefee);
		
	}
	@Override
	public List<Latefee> getAllLatefeeRecords() {
		
		return new ArrayList<>(latefeerecords.values());
	}
	
	@Override
	public void deleteRecord(int memberIDToDelete) {
		Latefee latefee = latefeerecords.get(memberIDToDelete);
		if(latefee != null) {
			latefeerecords.remove(memberIDToDelete);
		}
		else {
			System.out.println("Record Not Found");
		}
		
	}		
	
}
