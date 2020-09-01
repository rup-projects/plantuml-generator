package org.rup.pumlgenerator.controllers;

import java.nio.file.Path;

public interface DiagramGenerator {
    void generateFromDirectory(Path path);
}
