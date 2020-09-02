package org.rup.pumlgenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.rup.pumlgenerator.views.GenerationView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
@RequiredArgsConstructor
public class PumlgeneratorApplication implements CommandLineRunner {

	private final GenerationView generationView;

	public static void main(String[] args) {
		SpringApplication.run(PumlgeneratorApplication.class, args);
	}

	@Override
	public void run(String... commandLineArgs) throws Exception {
		log.info("EXECUTING : command line runner");
		log.info("Reading from: " + commandLineArgs[0]);

		generationView.generateFromDirectory(commandLineArgs);

		log.info("EXECUTING : End execution");
	}
}
