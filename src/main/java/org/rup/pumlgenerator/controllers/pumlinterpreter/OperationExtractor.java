package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;
import org.rup.pumlgenerator.utils.Matchers;

import java.util.List;

public class OperationExtractor implements PumlClassInterpreter {
    @Override
    public List<PumlClass> interpret(PumlClassContext context) {
        context.getPumlClasses().forEach(pumlClass -> addRelationsFrom(context.getSourceCode(), pumlClass));

        return context.getPumlClasses();
    }

    private static void addRelationsFrom(List<String> lines, PumlClass pumlClass) {
        for (String line : lines) {
            if (Matchers.isRelation(pumlClass.getName(), line)) {
                pumlClass.addRelation(line);
            }
        }
    }
}
