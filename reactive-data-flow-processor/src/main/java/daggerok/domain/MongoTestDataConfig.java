package daggerok.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Configuration
public class MongoTestDataConfig {

  @Bean InitializingBean init(final MongoOperations operations,
                              final MessageRepository messages) {

    /* mongo capped begin */
    // operations.createCollection(Message.class, new CollectionOptions(1_000_000_000_000_000L, null, true));
    CollectionOptions options = CollectionOptions.empty()
                                                 .capped()
                                                 .size(1_000_000_000_000_000L)
                                                 ////"errmsg": "max in a capped collection has to be < 2^31 or not set"
                                                 //.maxDocuments(1_000_000_000_000_000L);
                                                 .maxDocuments(Integer.MAX_VALUE);

    if (operations.collectionExists(Message.class))
      operations.dropCollection(Message.class);

    operations.createCollection(Message.class, options);
    /* mongo capped end */

    final AtomicLong atomicLong = new AtomicLong(1);
    final LocalDateTime base = LocalDateTime.now();

    return () -> Flux.just(1, 2, 3)
                     .map(i -> new Message().setBody("message " + i)
                                            .setAt(base.plusMinutes(atomicLong.incrementAndGet())))
                     .flatMap(messages::save)
                     .subscribe(r -> {
                       //operations.executeCommand("db.runCommand({ convertToCapped: 'reservation', size: 1111111111 })");
                       log.info("message saved: {}", r);
                     });
  }
}
