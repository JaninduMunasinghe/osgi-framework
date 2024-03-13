package com.mtit.reviewproducer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewProducerImpl implements ReviewProducer{
	
	private Map<Integer, Review> reviews = new HashMap<>();
	
	long millis = System.currentTimeMillis();  
    java.sql.Date date = new java.sql.Date(millis);
	
	private int nextRatingRecordId = 1;

	@Override
	public void addReview(Review review) {
		review.setReviewId(nextRatingRecordId++);
		if(review.getCreatedAt() == null) {
		    review.setCreatedAt(date);
		}
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

	@Override
	public Review retrieveReviewById(int reviewIdToRetrieve) {
		List<Review> reviewList = getAllReviews();
		
		for(Review review : reviewList) {
			if(review.getReviewId() == reviewIdToRetrieve) {
				return review;
			}
		}
		return null;
	}
	
	@Override
	public Review updateReview(Review review) {
		if(review.getCreatedAt() != null) {
			review.setLastModifiedAt(date);
		}
		
		reviews.remove(review.getReviewId());
		reviews.put(review.getReviewId(), review);
		
		return review;
	}

	@Override
	public List<Map.Entry<Integer, Double>> getRecommendedBooks() {
		Map<Integer, List<Integer>> bookRatingList = new HashMap<>();
		List<Review> reviewList = getAllReviews();
		
		for(Review review : reviewList) {
			int bookId = review.getBookId();
			int rating = review.getRating();
			
			if(!bookRatingList.containsKey(bookId)) {
				bookRatingList.put(bookId, new ArrayList<>());
			}
			bookRatingList.get(bookId).add(rating);
		}
		
		Map<Integer, Double> overallRatingList = new HashMap<>();
		
		//for single key value pair
		for(Map.Entry<Integer, List<Integer>> entry : bookRatingList.entrySet()) {
			int bookId = entry.getKey();
			List<Integer> ratings = entry.getValue();
			
			double overallRating = ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
			overallRatingList.put(bookId, overallRating);
		}
		
		List<Map.Entry<Integer, Double>> sortedRatingBookList = new ArrayList<>(overallRatingList.entrySet());
		sortedRatingBookList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		
		return sortedRatingBookList;
	}
}
