package org.rup.pumlgenerator.controllers.pumlinterpreter;

import org.junit.jupiter.api.Test;
import org.rup.pumlgenerator.model.PumlClass;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralInterpreterTest {

    @Test
    void interpretTest_havingEmptySourceCodeList_whenInterpret_thenGetEmptyListResult() {
        // Arrangement
        PumlClassContext pumlClassContext = new PumlClassContext(Collections.emptyList());

        // Actions
        List<PumlClass> result = new GeneralInterpreter().interpret(pumlClassContext);

        // Asserts
        assertThat(result).isEmpty();
    }

    @Test
    void interpretTest_havingOneDeclaredClassForEachType_whenInterpret_thenGetAllTheClasses() {
        // Arrangement
        PumlClassContext context = new PumlClassContext(Stream.of(
                "controller ControllerClass",
                "view ViewClass",
                "model EntityClass"
        ).collect(Collectors.toList()));

        // Actions
        List<PumlClass> result = new GeneralInterpreter().interpret(context);

        // Asserts
        assertThat(result).hasSize(3);
    }

    @Test
    void interpretTest_havingOneClassLinesWithAttributesAndReferences_whenInterpret_thenClassWithAllAttributesAndReferences() {
        // Arrangement
        PumlClassContext context = new PumlClassContext(Stream.of(
                "controller ExampleClass",
                "ReferenceLeft --> ExampleClass",
                "note on link",
                "operation()",
                "end note",
                "ExampleClass --> ReferenceRight"
        ).collect(Collectors.toList()));

        // Actions
        PumlClass result = new GeneralInterpreter().interpret(context).get(0);

        // Asserts
        assertThat(result.getName()).isEqualTo("ExampleClass");
        assertThat(result.getType()).isEqualTo("controller");

        assertThat(result.getAttributes()).hasSize(1);
        assertThat(result.getAttributes()).contains("operation()");

        assertThat(result.getRelations()).hasSize(2);
        assertThat(result.getRelations()).contains("ReferenceLeft --> ExampleClass");
        assertThat(result.getRelations()).contains("ExampleClass --> ReferenceRight");
    }
}
