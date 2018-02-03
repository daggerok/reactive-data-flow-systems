package daggerok;

import daggerok.routes.RoutesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ RoutesConfig.class, })
public class ReactiveWebfluxDataFlowApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveWebfluxDataFlowApiApplication.class, args);
  }
}
