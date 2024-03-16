package com.mtit.bookcatalogueproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class CatalogueProducerImpl implements CatalogueProducer {

	private Map<Integer, Catalogue> catalogues = new HashMap<>(); 

	private int nextBookId = 1;
	
	@Override
	public void addBook(Catalogue catalogue) {
		catalogue.setBookID(nextBookId++);
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
			System.out.println("Book Not Found");
		}
		
	}
	
	@Override
    public void updateBook(int bookID, Catalogue updatedCatalogue) {
        if (catalogues.containsKey(bookID)) {
            catalogues.put(bookID, updatedCatalogue);
            System.out.println("Catalogue Updated Successfully");
        } else {
            System.out.println("Book Not Found");
        }
    }

	@Override
	public Catalogue getCatalogue(int bookID) {
        return catalogues.get(bookID);
	}

}
