package az.movieapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {

    Long id;

    String title;

    String director;

    String releaseYear;

    String genre;

    BigDecimal imdbRating;
}
