package org.rup.pumlgenerator.controllers;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileUtilsFacade {
    public List<File> readFilesFromDirectory(Path directory) {
        File colaborationDiagramsDirectory = directory.toFile();
        File[] diagrams = colaborationDiagramsDirectory.listFiles();

        return Arrays.stream(diagrams).collect(Collectors.toList());
    }

    List<String> convertFilesToString(List<File> files) throws IOException {
        List<String> lineList = new ArrayList<>();
        for (File diagramFile : files) {
            Stream<String> lines = Files.lines(diagramFile.toPath());
            lineList.addAll(lines.collect(Collectors.toList()));
        }
        return lineList.stream()
                .filter(Strings::isNotBlank)
                .collect(Collectors.toList());
    }
}
