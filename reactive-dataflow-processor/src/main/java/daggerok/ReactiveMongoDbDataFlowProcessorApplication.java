package daggerok;

import daggerok.routes.RoutesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@Import({ RoutesConfig.class, })
@EnableReactiveMongoRepositories(considerNestedRepositories = true)
public class ReactiveMongoDbDataFlowProcessorApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveMongoDbDataFlowProcessorApplication.class, args);
  }
}
