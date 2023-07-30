package sandipchitale.nimbus.jwt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(JwtApplication.class)
				.headless(false)
				.run(args);
	}

}
