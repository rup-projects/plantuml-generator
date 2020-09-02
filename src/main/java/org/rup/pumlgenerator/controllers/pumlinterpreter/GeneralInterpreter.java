package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;
import org.rup.pumlgenerator.utils.Matchers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GeneralInterpreter implements PumlClassInterpreter {
    @Override
    public List<PumlClass> interpret(List<String> context) {
        Set<String> classesList = context.stream()
                .filter(s -> s.startsWith("model") || s.startsWith("view") || s.startsWith("controller"))
                .collect(Collectors.toSet());

        List<PumlClass> pumlClasses = classesList.stream()
                .map(s -> new PumlClass(s.split(" ")[1], s.split(" ")[0]))
                .collect(Collectors.toList());

        pumlClasses.forEach(pumlClass -> addAttributesFrom(context, pumlClass));
        pumlClasses.forEach(pumlClass -> addRelationsFrom(context, pumlClass));

        return pumlClasses;
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

    private static void addRelationsFrom(List<String> lines, PumlClass pumlClass) {
        for (String line : lines) {
            if (Matchers.isRelation(pumlClass.getName(), line)) {
                pumlClass.addRelation(line);
            }
        }
    }


}
