package com.mtit.bookcatalogueproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class CatalogueProducerImpl implements CatalogueProducer {

	private Map<Integer, Catalogue> catalogues = new HashMap<>(); 
	private int nextCatelogueID = 1;
	
	@Override
	public void addBook(Catalogue catalogue) {
		catalogue.setBookID(nextCatelogueID++);
		catalogues.put(catalogue.getBookID(), catalogue);
	}

	@Override
	public List<Catalogue> getAllCatalogues() {
		return new ArrayList<>(catalogues.values());
	}

	@Override
	public void deleteBook(int bookIDToDelete) {
		Catalogue catalogue = catalogues.get(bookIDToDelete);
		if(catalogue != null) {
			catalogues.remove(bookIDToDelete);
		}
		else {
			System.out.println("Catalogue Not Found");
		}
		
	}

}
