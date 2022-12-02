package camp.nextstep.edu.missionutils.test;

import camp.nextstep.edu.missionutils.Mocking;
import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.Mockito.mockStatic;

public class Assertions {
    private static final Duration SIMPLE_TEST_TIMEOUT = Duration.ofSeconds(1L);
    private static final Duration RANDOM_TEST_TIMEOUT = Duration.ofSeconds(10L);

    private Assertions() {
    }

    public static void assertSimpleTest(final Executable executable) {
        assertTimeoutPreemptively(SIMPLE_TEST_TIMEOUT, executable);
    }

    public static void assertRandomNumberInListTest(
        final Executable executable,
        final Integer value,
        final Integer... values
    ) {
        assertRandomTest(executable, Mocking.ofRandomNumberInList(value, values));
    }

    public static void assertRandomNumberInRangeTest(
        final Executable executable,
        final Integer value,
        final Integer... values
    ) {
        assertRandomTest(executable, Mocking.ofRandomNumberInRange(value, values));
    }

    @SafeVarargs
    public static void assertRandomUniqueNumbersInRangeTest(
        final Executable executable,
        final List<Integer> value,
        final List<Integer>... values
    ) {
        assertRandomTest(executable, Mocking.ofRandomUniqueNumbersInRange(value, values));
    }

    @SafeVarargs
    public static <T> void assertShuffleTest(
        final Executable executable,
        final List<T> value,
        final List<T>... values
    ) {
        assertRandomTest(executable, Mocking.ofShuffle(value, values));
    }

    public static void assertRandomTest(
            final Executable executable,
            final Mocking... mockings
    ) {
        assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
            try (final MockedStatic<Randoms> mock = mockStatic(Randoms.class)) {
                Arrays.stream(mockings).forEach(mocking -> mocking.stub(mock));
                executable.execute();
            }
        });
    }
}
