package datahub.skhynix.com.striimtqlparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StriimTqlParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(StriimTqlParserApplication.class, args);
	}

}
