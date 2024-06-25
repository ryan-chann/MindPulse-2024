package edu.mingjun.mindpulse;

import edu.mingjun.mindpulse.config.AwsSsoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.io.IOException;


@SpringBootApplication
public class MindpulseApplication {
	public static void main(String[] args) throws IOException {
		AwsSsoConfig.main();

		SpringApplication.run(MindpulseApplication.class, args);
	}


}
