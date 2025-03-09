package test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.BlueprintsScoringApp;

public class RoundTripTests {

    @ParameterizedTest
    @ValueSource(strings = { "01", "02", "03", "04", "05", "06", "07", "08" })
    public void level_50(String testNum) throws Exception {
        BlueprintsScoringApp app = new BlueprintsScoringApp(
                buildingPath(50, testNum),
                blueprintPath(50, testNum),
                actualResultPath(50, testNum));
        app.run();

        assertIterableEquals(expectedFileLines(50, testNum), actualFileLines(50, testNum));
    }

    private String buildingPath(int level, String testNum) {
        return String.format("res/marking/LEVEL-%d/building-%s.txt", level, testNum);
    }

    private String blueprintPath(int level, String testNum) {
        return String.format("res/marking/LEVEL-%d/blueprint-%s.txt", level, testNum);
    }

    private String expectedResultPath(int level, String testNum) {
        return String.format("res/marking/LEVEL-%d/expected-scoring-result-%s.txt", level, testNum);
    }

    private String actualResultPath(int level, String testNum) {
        return String.format("res/marking/LEVEL-%d/scoring-result-%s.txt", level, testNum);
    }

    private List<String> expectedFileLines(int level, String testNum) throws Exception {
        Path expectedResultPath = Paths.get(expectedResultPath(50, testNum));
        return Files.readAllLines(expectedResultPath);
    }

    private List<String> actualFileLines(int level, String testNum) throws Exception {
        Path actualResultPath = Paths.get(actualResultPath(50, testNum));
        return Files.readAllLines(actualResultPath);
    }

}
