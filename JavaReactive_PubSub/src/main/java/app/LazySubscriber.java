package app;

public class LazySubscriber<T> extends SubscriberImpl<T> {

    private final int sleepTime;

    public LazySubscriber(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void onNext(T item) {
        System.out.println("Subscriber" + id + ": Waiting " + sleepTime + "ms");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Subscriber" + id + " received: " + item);
        consumedElements.add(item);
        subscription.request(1);
    }
}
