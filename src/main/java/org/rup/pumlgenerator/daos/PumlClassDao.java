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

}
