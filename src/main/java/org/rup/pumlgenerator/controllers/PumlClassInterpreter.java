package org.rup.pumlgenerator.controllers;

import org.rup.pumlgenerator.model.PumlClass;

import java.util.List;

public interface PumlClassInterpreter {
    List<PumlClass> interpret(String context);
}
