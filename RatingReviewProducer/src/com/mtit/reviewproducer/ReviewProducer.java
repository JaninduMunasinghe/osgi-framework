package com.mtit.reviewproducer;

import java.util.List;
import java.util.Map;

public interface ReviewProducer {

	public void addReview(Review review);
	public List<Review> getAllReviews();
	public Review retrieveReviewById(int reviewIdToRetrieve);
	public Review updateReview(Review review);
	public void deleteReview(int reviewId);
	public List<Map.Entry<Integer, Double>> getRecommendedBooks();
}
