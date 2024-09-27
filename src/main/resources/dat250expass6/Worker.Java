package dat250expass6;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Worker {
    private final static String QUEUE_NAME = "hello"; // Ensure this matches your producer queue name

    public static void main(String[] argv) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Set your RabbitMQ server host

        try (Connection connection = factory.newConnection(); 
             Channel channel = connection.createChannel()) {
             
            // Declare the queue (make sure it matches the producer)
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    System.err.println("Worker interrupted: " + e.getMessage());
                } finally {
                    System.out.println(" [x] Done");
                }
            };

            boolean autoAck = true; // Acknowledge messages automatically
            channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000); // Simulate work
        }
    }
}
