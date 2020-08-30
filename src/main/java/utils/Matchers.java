package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matchers {
    public static boolean isRelation(String name, String line) {
        Pattern pattern = Pattern.compile(name + " .*>");
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }
}
