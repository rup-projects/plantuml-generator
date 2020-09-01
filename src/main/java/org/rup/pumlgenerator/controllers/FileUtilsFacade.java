package org.rup.pumlgenerator.controllers;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileUtilsFacade {
    public List<File> readFilesFromDirectory(Path directory) {
        File colaborationDiagramsDirectory = directory.toFile();
        File[] diagrams = colaborationDiagramsDirectory.listFiles();

        return Arrays.stream(diagrams).collect(Collectors.toList());
    }

    String convertFilesToString(List<File> files) {
        return null;
    }
}
