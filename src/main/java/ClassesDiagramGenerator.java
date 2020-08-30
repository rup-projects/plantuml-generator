import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassesDiagramGenerator {

    public void generate(List<File> colaborationDiagrams) throws IOException {
        List<String> classesList = getClassesList(colaborationDiagrams);
        classesList.forEach(ClassesDiagramGenerator::createFileDiagramFor);
    }

    private List<String> getClassesList(List<File> colaborationDiagrams) throws IOException {
        List<String> classesList = new ArrayList<>();

        for (File diagramFile : colaborationDiagrams) {
            Stream<String> lines = Files.lines(diagramFile.toPath());
            classesList.addAll(getClassesByType(lines));
        }
        return classesList;
    }

    private static void createFileDiagramFor(String className) {
        try {
            File newFile = new File(className + ".puml");
            boolean success = false;
            if (newFile.exists()) {
                System.out.println("Already exitst: " + newFile.getName());
                return;
            } else {
                success = newFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("@startuml");
            printWriter.println("class " + className);
            printWriter.println("@enduml");
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error generating file for: " + className);
        }
    }

    private static List<String> getClassesByType(Stream<String> lines) {
        return lines
                .filter(s -> s.startsWith("model") || s.startsWith("entity") || s.startsWith("controller"))
                .map(s -> s.split(" ")[1])
                .collect(Collectors.toList());
    }

}
