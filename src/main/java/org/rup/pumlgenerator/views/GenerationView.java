package org.rup.pumlgenerator.views;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.rup.pumlgenerator.controllers.ClassDiagramGeneratorService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
@Log4j2
public class GenerationView {

    private final ClassDiagramGeneratorService classDiagramGeneratorService;

    public void generateFromDirectory(String[] commandLineArgs) {
        Path path = Paths.get(commandLineArgs[0]);
        try {
            classDiagramGeneratorService.generateFromDirectory(path);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
