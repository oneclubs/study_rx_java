package common;

public final class ThreadUtils {

    private ThreadUtils() {}

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
