package org.rup.pumlgenerator.controllers;

import lombok.RequiredArgsConstructor;
import org.rup.pumlgenerator.controllers.pumlinterpreter.PumlClassContext;
import org.rup.pumlgenerator.controllers.pumlinterpreter.PumlClassInterpreter;
import org.rup.pumlgenerator.daos.PumlClassDao;
import org.rup.pumlgenerator.model.PumlClass;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

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
        List<PumlClass> pumlClassList = pumlClassInterpreter.interpret(new PumlClassContext(sourceCode));
        pumlClassList.forEach(pumlClassDao::save);
    }
}
