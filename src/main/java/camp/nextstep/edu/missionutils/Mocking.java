package camp.nextstep.edu.missionutils;

import org.mockito.MockedStatic;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;

public class Mocking<T> {

    /**
     * stubbing lambda verification
     * 예시)
     * () -> Randoms.pickNumberInList(anyList())
     */
    private final MockedStatic.Verification verification;

    // 반환할 첫 번째 값
    private final T value;

    /**
     * 첫 번째 값을 반환하고 나서 다음에 반환할 값들.
     * 예를 들면, verification을 처음 실행하면 value를 반환하고
     * 두 번째 실행하면 values[0]을 반환한다.
     */
    private final T[] values;

    private Mocking(final MockedStatic.Verification verification,
                    final T value,
                    final T... values) {
        this.verification = verification;
        this.value = value;
        this.values = values;
    }

    // enum으로 빼도 괜찮을 것 같은데..?
    public static Mocking<Integer> ofRandomNumberInList(final Integer value, final Integer... values) {
        return new Mocking<>(() -> Randoms.pickNumberInList(anyList()), value, values);
    }

    public static Mocking<Integer> ofRandomNumberInRange(final Integer value, final Integer... values) {
        return new Mocking<>(() -> Randoms.pickNumberInRange(anyInt(), anyInt()), value, values);
    }

    public static Mocking<List<Integer>> ofRandomUniqueNumbersInRange(final List<Integer> value, final List<Integer>... values) {
        return new Mocking<>(() -> Randoms.pickUniqueNumbersInRange(anyInt(), anyInt(), anyInt()), value, values);
    }

    public static <T> Mocking<List<T>> ofShuffle(final List<T> value, final List<T>... values) {
        return new Mocking<>(() -> Randoms.shuffle(anyList()), value, values);
    }

    public <S> void stub(final MockedStatic<S> mock) {
        mock.when(verification).thenReturn(value, Arrays.stream(values).toArray());
    }
}
