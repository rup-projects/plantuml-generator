package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;

import java.util.List;

public interface PumlClassInterpreter {
    List<PumlClass> interpret(List<String> context);
}
