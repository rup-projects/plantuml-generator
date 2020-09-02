package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneralInterpreter implements PumlClassInterpreter {
    @Override
    public List<PumlClass> interpret(List<String> context) {
        return null;
    }
}
