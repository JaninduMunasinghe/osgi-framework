package com.mtit.reviewproducer;

import java.util.List;

public interface ReviewProducer {

	public void addReview(Review review);
	public List<Review> getAllReviews();
	public void deleteReview(int reviewId);
}
