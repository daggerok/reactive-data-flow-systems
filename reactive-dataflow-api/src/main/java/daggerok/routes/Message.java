package daggerok.routes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Message implements Serializable {

  private static final long serialVersionUID = 8492869483112880655L;

  String body;
}
