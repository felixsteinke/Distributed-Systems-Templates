package app;


public class EagerSubscriber<T> extends SubscriberImpl<T> {

    @Override
    public void onNext(T item) {
        System.out.println("Subscriber" + id + " received: " + item);
        consumedElements.add(item);
        subscription.request(1);
    }
}
