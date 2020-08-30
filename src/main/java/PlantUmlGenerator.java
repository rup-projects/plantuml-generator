import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlantUmlGenerator {
    public static void main(String args[]) throws IOException {

        File colaborationDiagramsDirectory = new File(getColaborationDiagramsDirectory());
        File[] colaborationDiagrams = colaborationDiagramsDirectory.listFiles();
        for (File diagramFile : colaborationDiagrams) {
            Stream<String> lines = Files.lines(diagramFile.toPath());
            List<String> classes = lines
//                    .filter(s -> s.startsWith("view"))
//                    .filter(s -> s.startsWith("controller"))
                    .filter(s -> s.startsWith("model"))
//                    .map(s->s.split(" ")[1])
                    .collect(Collectors.toList());
            classes.forEach(s -> System.out.println(s));
        }

        File newFile = new File("newFile.puml");

        boolean success = false;
        if (newFile.exists()) {
            System.out.println("Already exitst: " + newFile.getName());
            System.exit(0);
            success = true;
        } else {
            success = newFile.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(newFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("@startuml");
        printWriter.println("class Example");
        printWriter.println("@enduml");
        printWriter.close();

        System.out.println(success ? "fine" : "failed");
    }

    private static String getColaborationDiagramsDirectory() {
        return "/media/pedro/data-projects/personal-workspace/rup-projects/documentation/analysisview/usecaseanalysis"
                + "/collaborationdiagrams/projectmanagement";
    }
}

