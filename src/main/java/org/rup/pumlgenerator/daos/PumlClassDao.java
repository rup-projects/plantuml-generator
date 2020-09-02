package org.rup.pumlgenerator.daos;

import lombok.extern.log4j.Log4j2;
import org.rup.pumlgenerator.model.PumlClass;
import org.rup.pumlgenerator.views.PumlClassPresenter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
@Log4j2
public class PumlClassDao {

    public void save(List<PumlClass> pumlClassList) {
        pumlClassList.forEach(this::createFileDiagramFor);
    }

    private void createFileDiagramFor(PumlClass pumlClass) {
        try {
            File newFile = new File(pumlClass.getName() + ".puml");
            if (newFile.exists()) {
                log.info("Already exitst: " + newFile.getName());
                return;
            } else {
                boolean isCreated = newFile.createNewFile();
                if (!isCreated) {
                    log.error("File cannot be created: " + newFile.getName());
                }
            }
            List<String> presentation = new PumlClassPresenter().present(pumlClass);
            PrintWriter printWriter;
            try (FileWriter fileWriter = new FileWriter(newFile)) {
                printWriter = new PrintWriter(fileWriter);
            }

            presentation.forEach(printWriter::println);

            printWriter.close();
        } catch (IOException e) {
            log.error("Error generating file for: " + pumlClass, e);
        }
    }
}
