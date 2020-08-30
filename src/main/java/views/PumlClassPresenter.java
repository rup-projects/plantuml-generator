package views;

import model.PumlClass;

import java.util.ArrayList;
import java.util.List;

public class PumlClassPresenter {
    public List<String> present(PumlClass pumlClass) {
        List<String> result = new ArrayList<>();
        result.add("@startuml");
        result.add("\tclass " + pumlClass.getName() + " {");
        pumlClass.getAttributes().forEach(attribute -> result.add("\t+" + attribute));
        pumlClass.getRelations().forEach(relation -> result.add("\t" + relation));
        result.add("\t}");
        result.add("@enduml");
        return result;
    }
}
