package app;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.logging.Logger;

abstract class SubscriberImpl<T> implements Subscriber<T> {
    private static final Logger LOGGER = Logger.getLogger(EagerSubscriber.class.getName());
    private static long globalId = 1;
    protected final long id = globalId++;
    protected final List<T> consumedElements = new LinkedList<>();
    protected Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(1);
        this.subscription = subscription;
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.warning("Subscriber" + id + " Error: " + throwable.getClass().getSimpleName() + " (" + throwable.getMessage() + ")");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        subscription.cancel();
        subscription = null;
        System.out.println("Subscriber" + id + " closes after " + consumedElements.size() + " consumed items.");
        consumedElements.clear();
    }
}
