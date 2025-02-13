package az.movieapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "movies")
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    String title;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Director cannot be blank")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    String director;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Release year cannot be blank")
    @Pattern(regexp = "\\d{4}", message = "Release year must be a valid 4-digit year")
    String releaseYear;

    @NotBlank(message = "Genre cannot be blank") // Genre boş ola bilməz
    @Size(min = 1, max = 50, message = "Genre must be between 1 and 50 characters")
    String genre;

    @Column(precision = 3, scale = 2)
    @DecimalMin(value = "0.00", message = "IMDb rating cannot be less than 0")
    @DecimalMax(value = "10.00", message = "IMDb rating cannot be more than 10")
    BigDecimal imdbRating;
}
