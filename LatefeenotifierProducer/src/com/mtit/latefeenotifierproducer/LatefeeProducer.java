package com.mtit.latefeenotifierproducer;


import java.util.List;



public interface LatefeeProducer {
	
	public void addLatefeeRecord(Latefee latefee);
	public List<Latefee> getAllLatefeeRecords();
	public void deleteRecord(int memberIDToDelete);

}
