package camp.nextstep.edu.missionutils.acceptance;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class ScannerBufferTest extends NsTest {
    private static final String ERROR_CAUSING_MESSAGE = "ERROR";
    private static final String NORMAL_MESSAGE = "MESSAGE";

    @Order(1)
    @TestMethodOrder(MethodOrderer.MethodName.class)
    @Nested
    class RunExceptionTest {
        @Test
        void test1() {
            assertSimpleTest(() ->
                assertThatIllegalArgumentException().isThrownBy(() ->
                    runException(ERROR_CAUSING_MESSAGE, ERROR_CAUSING_MESSAGE)
                )
            );
        }

        @Test
        void test2() {
            assertSimpleTest(() ->
                assertThatNoException().isThrownBy(() ->
                    run(NORMAL_MESSAGE)
                )
            );
        }
    }

    @Order(2)
    @TestMethodOrder(MethodOrderer.MethodName.class)
    @Nested
    class RunTest {
        @Test
        void test1() {
            assertSimpleTest(() ->
                assertThatNoException().isThrownBy(() ->
                    run(NORMAL_MESSAGE, ERROR_CAUSING_MESSAGE)
                )
            );
        }

        @Test
        void test2() {
            assertSimpleTest(() ->
                assertThatNoException().isThrownBy(() ->
                    run(NORMAL_MESSAGE)
                )
            );
        }
    }

    @Override
    protected void runMain() {
        final String input = Console.readLine();
        if (input.contains(ERROR_CAUSING_MESSAGE)) {
            throw new IllegalArgumentException("An error occurred because of this phrase: " + input);
        }
    }
}
