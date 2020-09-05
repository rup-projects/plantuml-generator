package org.rup.pumlgenerator.controllers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

class FileUtilsFacadeTest {

    @Test
    void readFilesFromDirectory() throws IOException {

        Path path = Path.of("src/test/resources/puml/collaboration");

        List<File> files = new FileUtilsFacade().readFilesFromDirectory(path);

        Assertions.assertThat(files).hasSize(2);
        Assertions.assertThat(files.get(0).getName()).isEqualTo("openInterventions.puml");
        Assertions.assertThat(files.get(1).getName()).isEqualTo("OpenPhases.puml");
    }

    @Test
    void convertFilesToString() throws IOException {

        FileUtilsFacade fileUtilsFacade = new FileUtilsFacade();
        Path path = Path.of("src/test/resources/puml/collaboration");
        List<File> files = fileUtilsFacade.readFilesFromDirectory(path);

        List<String> lines = fileUtilsFacade.convertFilesToString(files);

        Assertions.assertThat(lines).hasSize(82);
        Assertions.assertThat(lines.get(0)).isEqualTo("@startuml");
        Assertions.assertThat(lines.get(81)).isEqualTo("@enduml");
    }
}