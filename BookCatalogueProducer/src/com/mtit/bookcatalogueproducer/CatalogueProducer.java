package com.mtit.bookcatalogueproducer;

import java.util.List;


public interface CatalogueProducer {
	
	public void addBook(Catalogue catalogue);
	public List<Catalogue> getAllCatalogues();
	public void deleteBook(int bookIDToDelete);
	Catalogue getCatalogue(int bookID); 
	void updateBook(int bookID, Catalogue updatedCatalogue);

}
