package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;

import java.util.List;

// TODO Esto es mas como un pipe de operaciones
public interface PumlClassInterpreter {
    List<PumlClass> interpret(PumlClassContext context);
}
