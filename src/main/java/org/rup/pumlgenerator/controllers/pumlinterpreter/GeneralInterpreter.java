package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.rup.pumlgenerator.model.PumlClass;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneralInterpreter implements PumlClassInterpreter {

    private ClassExtractor classExtractor = new ClassExtractor();

    @Override
    public List<PumlClass> interpret(PumlClassContext context) {
        return classExtractor.interpret(context);
    }

}
