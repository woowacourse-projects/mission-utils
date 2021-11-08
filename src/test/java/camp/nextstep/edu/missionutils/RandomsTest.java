package camp.nextstep.edu.missionutils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class RandomsTest {
    @DisplayName("목록에 있는 숫자 중 하나를 반환한다.")
    @CsvSource({"100, 200, 300", "1, 2, 3", "-1, 0, 1"})
    @ParameterizedTest
    void pickNumberInList(final int number1, final int number2, final int number3) {
        final List<Integer> numbers = Arrays.asList(number1, number2, number3);
        for (int i = 0; i < 1000; i++) {
            final int actual = Randoms.pickNumberInList(numbers);
            assertThat(actual).isIn(numbers);
        }
    }

    @DisplayName("목록이 비어 있으면 예외가 발생한다.")
    @Test
    void pickNumberInList() {
        final List<Integer> emptyList = Collections.emptyList();
        assertThatThrownBy(() -> Randoms.pickNumberInList(emptyList))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자 범위를 지정하면 시작 또는 끝 숫자를 포함하여 범위의 숫자를 반환한다.")
    @CsvSource({"1, 1", "1, 2", "0, 10", "-1, 0"})
    @ParameterizedTest
    void pickNumberInRange(final int startInclusive, final int endInclusive) {
        for (int i = 0; i < 1000; i++) {
            int actual = Randoms.pickNumberInRange(startInclusive, endInclusive);
            assertThat(actual).isBetween(startInclusive, endInclusive);
        }
    }

    @DisplayName("시작 숫자가 끝 숫자보다 크면 예외가 발생한다.")
    @CsvSource({"1, 0", "1, -1", "9, -2", "-1, -3"})
    @ParameterizedTest
    void pickNumberInRangeWithLargeStartInclusive(final int startInclusive, final int endInclusive) {
        assertThatThrownBy(
            () -> Randoms.pickNumberInRange(startInclusive, endInclusive)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("끝 숫자가 Integer.MAX_VALUE보다 크거나 같으면 예외가 발생한다.")
    @CsvSource({"1, 0x7fffffff", "0, 0x7fffffff"})
    @ParameterizedTest
    void pickNumberInRangeWithLargeEndInclusive(final int startInclusive, final int endInclusive) {
        assertThatThrownBy(
            () -> Randoms.pickNumberInRange(startInclusive, endInclusive)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자 범위 내에서 지정된 개수만큼 겹치지 않는 숫자를 반환한다.")
    @CsvSource({"-2, -1, 1", "-1, 1, 1", "1, 1, 1", "1, 2, 1", "1, 3, 1", "1, 3, 2", "1, 3, 3"})
    @ParameterizedTest
    void pickUniqueNumbersInRange(final int startInclusive, final int endInclusive, final int count) {
        final List<Integer> actual = Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count);
        assertAll(
            () -> assertThat(actual).hasSize(count),
            () -> assertThat(actual).doesNotHaveDuplicates()
        );
    }

    @DisplayName("개수가 음수이면 예외가 발생한다.")
    @CsvSource({"1, 2, -1", "1, 3, -2", "1, 3, -3", "1, 3, -4"})
    @ParameterizedTest
    void pickUniqueNumbersInRangeWithNegativeCount(final int startInclusive, final int endInclusive, final int count) {
        assertThatThrownBy(() -> Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("개수가 숫자 범위의 개수보다 크면 예외가 발생한다.")
    @CsvSource({"1, 1, 2", "1, 2, 3", "1, 3, 4", "-1, 3, 6", "-1, 4, 7"})
    @ParameterizedTest
    void pickUniqueNumbersInRangeWithLargeCount(final int startInclusive, final int endInclusive, final int count) {
        assertThatThrownBy(() -> Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("무작위로 섞인 새 목록을 반환한다.")
    @MethodSource("factory")
    @ParameterizedTest
    <T> void shuffle(final T o1, final T o2, final T o3) {
        final List<T> actual = Randoms.shuffle(Arrays.asList(o1, o2, o3));
        assertThat(actual).containsExactlyInAnyOrder(o1, o2, o3);
    }

    private static Stream<Arguments> factory() {
        return Stream.of(
            Arguments.of(1, 2, 3),
            Arguments.of("a", "b", "c")
        );
    }
}
