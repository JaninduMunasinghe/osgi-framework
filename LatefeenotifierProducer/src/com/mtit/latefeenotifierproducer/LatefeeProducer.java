package com.mtit.latefeenotifierproducer;


import java.util.List;



public interface LatefeeProducer {
	
	public void addLatefeeRecord(LatefeeCalculateData latefee);
	public List<LatefeeCalculateData> getAllLatefeeRecords();
	public void deleteRecord(int memberIDToDelete);
	LatefeeCalculateData getLatefeeRecord(int memberID); // New method for retrieving a specific record
	void updateLatefeeRecord(int memberID, LatefeeCalculateData updatedLatefeeData); // New method for updating a record

}
