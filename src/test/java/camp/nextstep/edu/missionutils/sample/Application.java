package camp.nextstep.edu.missionutils.sample;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        final String name = askName();
        String nickname = null;
        while (Objects.isNull(nickname)) {
            nickname = askNickname(name);
        }
        final int age = Randoms.pickNumberInRange(1, 99);
        System.out.println(String.format("%s(%s) is %d years old.", nickname, name, age));
    }

    private static String askName() {
        System.out.println("what is your name?");
        final String name = Console.readLine();
        if (name.length() > 5) {
            throw new IllegalArgumentException("[ERROR] name cannot exceed 5 characters.");
        }
        return name;
    }

    private static String askNickname(final String name) {
        System.out.println("what is your nickname?");
        final String nickname = Console.readLine();
        if (name.equals(nickname)) {
            System.out.println("[ERROR] name and nickname cannot be the same.");
            return null;
        }
        return nickname;
    }
}
