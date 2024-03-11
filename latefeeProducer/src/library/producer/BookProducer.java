package library.producer;

import library.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class BookProducer implements Runnable {
    private final BlockingQueue<Task> queue;
    private final Scanner scanner;
    private final SimpleDateFormat dateFormat;

    public BookProducer(BlockingQueue<Task> queue, Scanner scanner) {
        this.queue = queue;
        this.scanner = scanner;
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    @Override
    public void run() {
        // Reading user input for book details
        System.out.println("Enter the book name:");
        String bookName = scanner.nextLine();

        Date borrowingDate = readDate("Enter the borrowing date (in dd-MM-yyyy format):");
        Date returnDate = readDate("Enter the return date (in dd-MM-yyyy format):");

        // Creating a task with user-provided inputs
        Task task = new Task(bookName, borrowingDate.getTime(), returnDate.getTime());
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Date readDate(String prompt) {
        Date date = null;
        while (date == null) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                date = dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter date in dd-MM-yyyy format.");
            }
        }
        return date;
    }
}
