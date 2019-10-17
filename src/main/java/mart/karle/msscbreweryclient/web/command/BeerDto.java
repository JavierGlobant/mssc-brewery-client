package mart.karle.msscbreweryclient.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {
  private UUID id;
  private String name;
  private String style;
  private Long upc;
}
