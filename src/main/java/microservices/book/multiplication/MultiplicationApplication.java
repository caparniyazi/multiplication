package microservices.book.multiplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiplicationApplication.class, args);
    }

    /**
     * For instance, if you wanted to change the JSON property naming to be snake-case instead of camel-case,
     * you could declare a custom ObjectMapper in the app configuration
     * that will be loaded instead of the default one.
     *
     * @return
     */
//    @Bean
//    public ObjectMapper objectMapper() {
//        return JsonMapper.builder().propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE).build();
//    }
}
