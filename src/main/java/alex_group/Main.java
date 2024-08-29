package alex_group;

public class Main {
    public static void main(String[] args)  {
       BlockingQueue<int[]> customBlockingQueue = new BlockingQueue<>(3);

        Runnable producer = () -> {
            try {
                int[] item = {(int) Thread.currentThread().getId()};
                System.out.println("Producer " + Thread.currentThread().getName() + " trying to enqueue: " + item[0]);
                customBlockingQueue.enqueue(item);
                System.out.println("Producer " + Thread.currentThread().getName() + " enqueued: " + item[0]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                System.out.println("Consumer " + Thread.currentThread().getName() + " trying to dequeue...");
                int[] item = customBlockingQueue.dequeue();
                System.out.println("Consumer " + Thread.currentThread().getName() + " dequeued: " + item[0]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
        Thread producer1 = new Thread(producer, "P1");
        Thread producer2 = new Thread(producer, "P2");
        Thread producer3 = new Thread(producer, "P3");
        Thread producer4 = new Thread(producer, "P4");

        Thread consumer1 = new Thread(consumer, "C1");
        Thread consumer2 = new Thread(consumer, "C2");
        Thread consumer3 = new Thread(consumer, "C3");

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();

        try {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();

            consumer1.join();
            consumer2.join();
            consumer3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Test completed.");
    }
}