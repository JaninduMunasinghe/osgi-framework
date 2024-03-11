package com.mtit.BorrowingRecordProducer;

import java.util.List;

public interface RecordProducer {
	
	public void addRecord(Record record);
	public List<Record> getAllRecords();
	public void deleteRecord(int memberIDToDelete);

}
