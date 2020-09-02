package org.rup.pumlgenerator.controllers;

import lombok.RequiredArgsConstructor;
import org.rup.pumlgenerator.controllers.pumlinterpreter.PumlClassInterpreter;
import org.rup.pumlgenerator.daos.PumlClassDao;
import org.rup.pumlgenerator.model.PumlClass;
import org.rup.pumlgenerator.utils.Matchers;
import org.rup.pumlgenerator.views.PumlClassPresenter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ClassDiagramGeneratorService implements DiagramGenerator {

    private final PumlClassDao pumlClassDao;
    private final FileUtilsFacade fileUtilsFacade;
    private final PumlClassInterpreter pumlClassInterpreter;

    @Override
    public void generateFromDirectory(Path path) throws IOException {
        List<File> files = fileUtilsFacade.readFilesFromDirectory(path);
        List<String> sourceCode = fileUtilsFacade.convertFilesToString(files);
        List<PumlClass> pumlClassList = pumlClassInterpreter.interpret(sourceCode);
        pumlClassDao.save(pumlClassList);
    }

    public void generate(List<File> colaborationDiagrams) throws IOException {
        List<PumlClass> classesList = getPumlClassList(colaborationDiagrams);
        classesList.forEach(ClassDiagramGeneratorService::createFileDiagramFor);
    }

    private List<PumlClass> getPumlClassList(List<File> colaborationDiagrams) throws IOException {
        List<String> allLines = getAllLinesDiagrams(colaborationDiagrams);
        List<PumlClass> pumlClasses = createPumlClasses(allLines);

        return pumlClasses;
    }

    private List<String> getAllLinesDiagrams(List<File> colaborationDiagrams) throws IOException {
        List<String> allLinesCollaborationDiagrams = new ArrayList<>();
        for (File diagramFile : colaborationDiagrams) {
            Stream<String> lines = Files.lines(diagramFile.toPath());
            allLinesCollaborationDiagrams.addAll(lines.collect(Collectors.toList()));
        }
        return allLinesCollaborationDiagrams;
    }

    private static void createFileDiagramFor(PumlClass pumlClass) {
        try {
            File newFile = new File(pumlClass.getName() + ".puml");
            boolean success = false;
            if (newFile.exists()) {
                System.out.println("Already exitst: " + newFile.getName());
                return;
            } else {
                success = newFile.createNewFile();
            }
            List<String> presentation = new PumlClassPresenter().present(pumlClass);
            FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            presentation.forEach(line -> printWriter.println(line));

            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error generating file for: " + pumlClass);
        }
    }

    private static List<PumlClass> createPumlClasses(List<String> lines) {
        Set<String> classesList = lines.stream()
                .filter(s -> s.startsWith("model") || s.startsWith("entity") || s.startsWith("controller"))
                .collect(Collectors.toSet());

        List<PumlClass> pumlClasses = classesList.stream()
                .map(s -> new PumlClass(s.split(" ")[1], s.split(" ")[0]))
                .collect(Collectors.toList());

        pumlClasses.forEach(pumlClass -> addAttributesFrom(lines, pumlClass));
        pumlClasses.forEach(pumlClass -> addRelationsFrom(lines, pumlClass));

        return pumlClasses;
    }

    private static void addAttributesFrom(List<String> lines, PumlClass pumlClass) {
        for (int index = 0; index < lines.size(); index++) {
            if (lines.get(index).endsWith("> " + pumlClass.getName())) {
                pumlClass.addRelation(lines.get(index));
                index++;
                if (lines.get(index).contains("note on link")) {
                    index++;
                    while (!lines.get(index).contains("end note")) {
                        pumlClass.addAttribute(lines.get(index));
                        index++;
                    }
                }
            }
        }
    }

    private static void addRelationsFrom(List<String> lines, PumlClass pumlClass) {
        for (int index = 0; index < lines.size(); index++) {
            if (Matchers.isRelation(pumlClass.getName(), lines.get(index))) {
                pumlClass.addRelation(lines.get(index));
            }
        }
    }
}
