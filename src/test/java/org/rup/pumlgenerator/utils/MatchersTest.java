package org.rup.pumlgenerator.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MatchersTest {

    @Test
    public void isRelationTest() {
        // Arrangement
        String line = "SplitActivityView o.right.> UseCase";
        String className = "SplitActivityView";

        // Actions
        boolean result = Matchers.isRelation(className, line);

        // Asserts
        assertThat(result).isTrue();
    }

    @Test
    public void isRelationTest_thenFalse() {
        // Arrangement
        String line = "MergeActivityController -down-> ActivitiesDao";
        String className = "Activity";

        // Actions
        boolean result = Matchers.isRelation(className, line);

        // Asserts
        assertThat(result).isFalse();
    }
}
