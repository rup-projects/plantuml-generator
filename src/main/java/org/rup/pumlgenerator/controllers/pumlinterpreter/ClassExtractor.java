package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassExtractor implements PumlClassInterpreter {

    private RelationExtractor relationExtractor = new RelationExtractor();

    @Override
    public List<PumlClass> interpret(PumlClassContext context) {
        Set<String> classesList = context.getSourceCode().stream()
                .filter(s -> s.startsWith("model") || s.startsWith("view") || s.startsWith("controller"))
                .collect(Collectors.toSet());

        List<PumlClass> pumlClasses = classesList.stream()
                .map(s -> new PumlClass(s.split(" ")[1], s.split(" ")[0]))
                .collect(Collectors.toList());

        context.setPumlClasses(pumlClasses);

        return relationExtractor.interpret(context);
    }
}
