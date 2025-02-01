import org.example.services.statistics.StateDiff;
import org.example.services.statistics.StateDiffDetector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StateDiffTest {
    @ParameterizedTest
    @MethodSource("generateData")
    public void detectorTest(Map<String,String> yesterdayData, Map<String,String> todayData, StateDiff expected) {
        var result = StateDiffDetector.findChanges(yesterdayData, todayData);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void parallelDetectorTest(Map<String,String> yesterdayData,
                                     Map<String,String> todayData,
                                     StateDiff expected) throws InterruptedException {
        var result = StateDiffDetector.parallelFindChanges(yesterdayData, todayData);
        Assertions.assertEquals(result, expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                // no changes
                Arguments.arguments(
                        Map.of(
                                "https://example.com/page1", "<html>Old content</html>"
                        ),
                        Map.of(
                                "https://example.com/page1", "<html>Old content</html>"
                        ),
                        new StateDiff(
                                List.of(),
                                List.of(),
                                List.of()
                        )
                ),
                // update content in page
                Arguments.arguments(
                        Map.of(
                                "https://example.com/page1", "<html>Old content</html>"
                        ),
                        Map.of(
                                "https://example.com/page1", "<html>New content</html>"
                        ),
                        new StateDiff(
                                List.of(),
                                List.of(),
                                List.of("https://example.com/page1")
                        )
                ),
                // remove page
                Arguments.arguments(
                        Map.of(
                                "https://example.com/page1", "<html>Old content</html>"
                        ),
                        Map.of(
                        ),
                        new StateDiff(
                                List.of("https://example.com/page1"),
                                List.of(),
                                List.of()
                        )
                ),
                // add new page
                Arguments.arguments(
                        Map.of(),
                        Map.of(
                                "https://example.com/page1", "<html>Old content</html>"
                        ),
                        new StateDiff(
                                List.of(),
                                List.of("https://example.com/page1"),
                                List.of()
                        )
                ),
                // empty data
                Arguments.arguments(
                        Map.of(),
                        Map.of(),
                        new StateDiff(
                                List.of(),
                                List.of(),
                                List.of()
                        )
                )
        );
    }
}
