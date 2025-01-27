package Lingtning.new_match42;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NewMatch42Application {

	public static void main(String[] args) {
		SpringApplication.run(NewMatch42Application.class, args);
	}

	/* Cors 설정 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("/*")
						.allowedHeaders("*")
//						.allowedOrigins("http://" + System.getenv("NCP_IP") + ":" + System.getenv("REACT_PORT"), "http://localhost:" + System.getenv("REACT_PORT"))
						.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
						.allowCredentials(true);
			}
		};
	}
}
