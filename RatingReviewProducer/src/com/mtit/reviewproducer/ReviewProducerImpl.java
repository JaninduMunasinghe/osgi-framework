package com.mtit.reviewproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewProducerImpl implements ReviewProducer{
	
	private Map<Integer, Review> reviews = new HashMap<>();
	
	private int nextRatingRecordId = 1;

	@Override
	public void addReview(Review review) {
		review.setReviewId(nextRatingRecordId++);
		reviews.put(review.getReviewId(), review);
	}

	@Override
	public List<Review> getAllReviews() {
		return new ArrayList<Review>(reviews.values());
	}

	@Override
	public void deleteReview(int reviewId) {
		Review review = reviews.get(reviewId);
		
		if(review != null) {
			reviews.remove(reviewId);
		} else {
			System.out.println("Review Not Found!");
		}
	}

}
