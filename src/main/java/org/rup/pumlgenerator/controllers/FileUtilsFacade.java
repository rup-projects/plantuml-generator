package org.rup.pumlgenerator.controllers;

import org.apache.logging.log4j.util.Strings;
import org.rup.pumlgenerator.utils.throwingfunctions.ThrowingFunction;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileUtilsFacade {

    public List<File> readFilesFromDirectory(Path directory) throws IOException {
        return Files.walk(directory)
                .map(Path::toFile)
                .filter(File::isFile)
                .collect(Collectors.toList());
    }

    public List<String> convertFilesToString(List<File> files) {
        return files.stream()
                .map(File::toPath)
                .flatMap(ThrowingFunction.unchecked(Files::lines))
                .filter(Strings::isNotBlank)
                .collect(Collectors.toList());
    }
}