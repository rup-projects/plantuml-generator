package org.rup.pumlgenerator.views;

import org.rup.pumlgenerator.model.PumlClass;

import java.util.ArrayList;
import java.util.List;

public class PumlClassPresenter {
    public List<String> present(PumlClass pumlClass) {
        List<String> result = new ArrayList<>();
        result.add("@startuml");
        result.add("\tclass " + pumlClass.getName() + " {");
        pumlClass.getAttributes().forEach(attribute -> result.add("\t+" + attribute));
        result.add("\t}");
        result.add("");
        pumlClass.getRelations().forEach(relation -> result.add("\t" + relation));
        result.add("@enduml");
        return result;
    }
}
