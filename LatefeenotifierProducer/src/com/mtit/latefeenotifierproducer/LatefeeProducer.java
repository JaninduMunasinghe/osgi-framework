package com.mtit.latefeenotifierproducer;


import java.util.List;



public interface LatefeeProducer {
	
	public void addLatefeeRecord(LatefeeCalculateData latefee);
	public List<LatefeeCalculateData> getAllLatefeeRecords();
	public void deleteRecord(int memberIDToDelete);

}
