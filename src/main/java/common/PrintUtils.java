package common;

public final class PrintUtils {

    private PrintUtils() {}

    public static void bar() {
        log("==========================================================");
    }

    public static void bar(String message) {
        bar();
        log(message);
        bar();
    }

    public static void line() {
        log("");
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
