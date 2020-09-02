package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;

import java.util.List;

public class RelationExtractor implements PumlClassInterpreter {

    private OperationExtractor operationExtractor = new OperationExtractor();

    @Override
    public List<PumlClass> interpret(PumlClassContext context) {
        context.getPumlClasses().forEach(pumlClass -> addAttributesFrom(context.getSourceCode(), pumlClass));

        return operationExtractor.interpret(context);
    }

    private static void addAttributesFrom(List<String> lines, PumlClass pumlClass) {
        for (int index = 0; index < lines.size(); index++) {
            if (lines.get(index).endsWith("> " + pumlClass.getName())) {
                pumlClass.addRelation(lines.get(index));
                index++;
                if (lines.get(index).contains("note on link")) {
                    index++;
                    while (!lines.get(index).contains("end note")) {
                        pumlClass.addAttribute(lines.get(index));
                        index++;
                    }
                }
            }
        }
    }
}
