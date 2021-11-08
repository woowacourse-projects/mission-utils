package camp.nextstep.edu.missionutils.sample;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("javajigi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE)
        );
    }

    @Test
    void 사용자의_입장을_고려한_예외_테스트() {
        assertSimpleTest(() -> {
                runException("jason", "jason");
                assertThat(output()).contains(ERROR_MESSAGE);
            }
        );
    }

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("jason", "제이슨");
                assertThat(output()).contains("제이슨(jason) is 30 years old.");
            },
            30
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
