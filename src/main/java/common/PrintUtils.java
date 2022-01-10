package common;

public final class PrintUtils {

    private PrintUtils() {}

    public static void bar() {
        internalLog("==========================================================");
    }

    public static void bar(String message) {
        bar();
        internalLog(message);
        bar();
    }

    public static void line() {
        internalLog("");
    }

    public static void threadLog(String message) {
        internalLogThread(message);
    }

    private static void internalLog(String message) {
        System.out.println(message);
    }

    private static void internalLogThread(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] : " + message);
    }
}
