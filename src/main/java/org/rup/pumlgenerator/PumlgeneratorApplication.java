package org.rup.pumlgenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.rup.pumlgenerator.controllers.ClassDiagramGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@Log4j2
@RequiredArgsConstructor
public class PumlgeneratorApplication implements CommandLineRunner {

	private final ClassDiagramGenerator classDiagramGenerator;

	public static void main(String[] args) {
		SpringApplication.run(PumlgeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");
		log.info("Reading from: " + args[0]);

		Path path = Paths.get(args[0]);
		classDiagramGenerator.generateFromDirectory(path);

		log.info("EXECUTING : End execution");
	}
}
