package org.rup.pumlgenerator;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class PumlgeneratorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PumlgeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");
		log.info("Reading from: " + args[0]);

	}
}
