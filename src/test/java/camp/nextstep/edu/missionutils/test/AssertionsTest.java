package camp.nextstep.edu.missionutils.test;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static org.assertj.core.api.Assertions.assertThat;

class AssertionsTest {
    @Test
    void pickNumberInRange() {
        assertRandomNumberInRangeTest(
            () -> {
                assertThat(Randoms.pickNumberInRange(0, 9)).isEqualTo(3);
                assertThat(Randoms.pickNumberInRange(0, 9)).isEqualTo(4);
                assertThat(Randoms.pickNumberInRange(0, 9)).isEqualTo(5);
                assertThat(Randoms.pickNumberInRange(0, 9)).isEqualTo(5);
                assertThat(Randoms.pickNumberInRange(0, 9)).isEqualTo(5);
            },
            3, 4, 5
        );
    }

    @Test
    void pickUniqueNumbersInRange() {
        assertRandomUniqueNumbersInRangeTest(
            () -> {
                assertThat(Randoms.pickUniqueNumbersInRange(1, 45, 6)).containsExactly(2, 4, 15, 23, 29, 38);
                assertThat(Randoms.pickUniqueNumbersInRange(1, 45, 6)).containsExactly(2, 13, 20, 30, 31, 41);
            },
            Arrays.asList(2, 4, 15, 23, 29, 38),
            Arrays.asList(2, 13, 20, 30, 31, 41)
        );
    }

    @Test
    void shuffle() {
        assertShuffleTest(
            () -> assertThat(Randoms.shuffle(Arrays.asList("a", "b", "c"))).containsExactly("b", "a", "c"),
            Arrays.asList("b", "a", "c")
        );
    }
}
