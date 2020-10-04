package org.rup.pumlgenerator.daos;

import lombok.extern.log4j.Log4j2;
import org.rup.pumlgenerator.model.PumlClass;
import org.rup.pumlgenerator.views.PumlClassPresenter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Component
@Log4j2
public class PumlClassDao {

    public void save(PumlClass pumlClass) {
        createFileDiagramFor(pumlClass);
    }

    private void createFileDiagramFor(PumlClass pumlClass) {
        try {
            String presentation = new PumlClassPresenter().presentAsString(pumlClass);
            Path outputPath = Path.of(String.format("%s.puml", pumlClass.getName()));
            if (Files.exists(outputPath)) {
                return;
            }
            Files.writeString(outputPath, presentation, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Error generating file for: " + pumlClass);
        }
    }
}
