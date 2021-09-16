package taller.com.co.movies.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Data
public class MovieDTO {
    private Long id;

    @NotEmpty(message = "el Titulo no puede estar vacio")
    private String title;

    @NotEmpty(message = "el director no puede estar vacio")
    private String director;

    @Range(min=1, max = 5, message = "la Calificación debe estar entre 1 y 5")
    private int rating;
}
