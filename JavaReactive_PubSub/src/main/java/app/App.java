package app;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        Subscriber<String> subscriber1 = new EagerSubscriber<>();
        Subscriber<String> subscriber2 = new LazySubscriber<>(200);
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        // when
        items.forEach(publisher::submit);
        Thread.sleep(1000);
        System.out.println("Publisher: Closing");
        publisher.close();
    }
}
