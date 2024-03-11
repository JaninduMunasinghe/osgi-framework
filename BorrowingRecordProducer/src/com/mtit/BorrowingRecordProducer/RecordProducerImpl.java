package com.mtit.BorrowingRecordProducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordProducerImpl implements RecordProducer {
	
	private Map<Integer, Record> records = new HashMap<>(); 
	private int nextRecordId = 1;

	@Override
	public void addRecord(Record record) {
		record.setMemberID(nextRecordId++);
		records.put(record.getMemberID(), record);
		
	}

	@Override
	public List<Record> getAllRecords() {
		return new ArrayList<>(records.values());
	}

	@Override
	public void deleteRecord(int memberID) {
		Record record = records.get(memberID);
		if(record != null) {
			records.remove(memberID);
		}
		else {
			System.out.println("Record Not Found");
		}
		
	}
	
}
