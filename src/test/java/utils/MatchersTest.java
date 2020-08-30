package utils;

import org.junit.Assert;
import org.junit.Test;


public class MatchersTest {

    @Test
    public void isRelationTest() {
        // Arrangement
        String line = "SplitActivityView o.right.> UseCase";
        String className = "SplitActivityView";

        // Actions
        boolean result = Matchers.isRelation(className, line);

        // Asserts
        Assert.assertTrue(result);
    }

    @Test
    public void isRelationTest_thenFalse() {
        // Arrangement
        String line = "MergeActivityController -down-> ActivitiesDao";
        String className = "Activity";

        // Actions
        boolean result = Matchers.isRelation(className, line);

        // Asserts
        Assert.assertFalse(result);
    }
}
