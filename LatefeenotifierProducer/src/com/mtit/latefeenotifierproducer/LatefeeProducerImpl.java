package com.mtit.latefeenotifierproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LatefeeProducerImpl implements LatefeeProducer {
	
	private Map<Integer, LatefeeCalculateData> latefeerecords = new HashMap<>(); 
	private int nextlatefeeRecordId = 1;
	
	
	@Override
	public void addLatefeeRecord(LatefeeCalculateData latefee) {
		latefee.setMemberID(nextlatefeeRecordId++);
		latefeerecords.put(latefee.getMemberID(), latefee);
		
	}
	@Override
	public List<LatefeeCalculateData> getAllLatefeeRecords() {
		
		return new ArrayList<>(latefeerecords.values());
	}
	
	@Override
	public void deleteRecord(int memberIDToDelete) {
		LatefeeCalculateData latefeedata = latefeerecords.get(memberIDToDelete);
		if(latefeedata != null) {
			latefeerecords.remove(memberIDToDelete);
			System.out.println("Record Deleted Successfully");
		}
		else {
			System.out.println("Record Not Found");
		}
		
	}		
	
	 @Override
	    public LatefeeCalculateData getLatefeeRecord(int memberID) {
	        return latefeerecords.get(memberID);
	    }

	    @Override
	    public void updateLatefeeRecord(int memberID, LatefeeCalculateData updatedLatefeeData) {
	        if (latefeerecords.containsKey(memberID)) {
	            latefeerecords.put(memberID, updatedLatefeeData);
	            System.out.println("Record Updated Successfully");
	        } else {
	            System.out.println("Record Not Found");
	        }
	    }
	}
