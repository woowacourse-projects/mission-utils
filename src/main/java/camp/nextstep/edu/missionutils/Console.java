package camp.nextstep.edu.missionutils;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Console {
    private static Scanner scanner;

    private Console() {
    }

    public static String readLine() {
        return getInstance().nextLine();
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    private static Scanner getInstance() {
        if (scanner == null || isClosed()) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    private static boolean isClosed() {
        try {
            final Field sourceClosedField = Scanner.class.getDeclaredField("sourceClosed");
            sourceClosedField.setAccessible(true);
            return sourceClosedField.getBoolean(scanner);
        } catch (final Exception e) {
            System.out.println("unable to determine if the scanner is closed.");
        }
        return true;
    }
}
