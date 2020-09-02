package org.rup.pumlgenerator.controllers.pumlinterpreter;

import lombok.Getter;
import lombok.Setter;
import org.rup.pumlgenerator.model.PumlClass;

import java.util.List;

@Getter
public class PumlClassContext {

    private final List<String> sourceCode;

    @Setter
    private List<PumlClass> pumlClasses;

    public PumlClassContext(List<String> sourceCode) {
        this.sourceCode = sourceCode;
    }
}
