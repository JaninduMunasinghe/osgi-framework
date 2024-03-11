package library.consumer;

import library.model.Task;

import java.util.concurrent.BlockingQueue;

public class BookConsumer implements Runnable {
    private final BlockingQueue<Task> queue;

    public BookConsumer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Task task = queue.take();
                calculateFee(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void calculateFee(Task task) {
        long borrowingDate = task.getBorrowingDate();
        long returnDate = task.getReturnDate();
        long timeDifference = returnDate - borrowingDate;
        long overdueDays = Math.max(0, (timeDifference - 14 * 24 * 60 * 60 * 1000) / (24 * 60 * 60 * 1000)); 
        long fee = 0;
        if (overdueDays <= 14) {
            fee = overdueDays * 3; 
        } else {
            fee = (14 * 3) + ((overdueDays - 14) * 20); 
        }
        System.out.println("Book: " + task.getBookName() + ", Due Fee: " + fee + " rupees");
    }
}
