import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PlantUmlGenerator {
    public static void main(String args[]) throws IOException {
        File colaborationDiagramsDirectory = new File(getColaborationDiagramsDirectory());
        File[] colaborationDiagrams = colaborationDiagramsDirectory.listFiles();

        new ClassesDiagramGenerator().generate(Arrays.stream(colaborationDiagrams).collect(Collectors.toList()));
    }

    private static String getColaborationDiagramsDirectory() {
        return "/media/pedro/data-projects/personal-workspace/rup-projects/documentation/analysisview/usecaseanalysis"
                + "/collaborationdiagrams/projectmanagement";
    }
}

