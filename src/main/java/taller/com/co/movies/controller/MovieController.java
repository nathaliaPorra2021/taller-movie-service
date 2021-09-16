package taller.com.co.movies.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import taller.com.co.movies.dto.MovieDTO;
import taller.com.co.movies.entity.Movie;
import taller.com.co.movies.service.MovieService;
import org.modelmapper.ModelMapper;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<MovieDTO>> listMovie(){
        List<Movie> movies = movieService.listAllMovies();
        if(movies.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));

    }

    @GetMapping(value = "/{idMovie}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable("idMovie") Long idMovie) {
        MovieDTO movie =   convertToDto(movieService.getMovieById(idMovie));
        if (null==movie){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movie, BindingResult result) throws ParseException {
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage((result)));
        }
        Movie movieCreate = movieService.createMovie(convertToEntity(movie));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(movieCreate));
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long id) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.ok("Eliminado con éxito");
        }catch(Exception e){
            return ResponseEntity.ok("No pudo eliminarse");
        }

    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";

        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return  jsonString;
    }

    private MovieDTO convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    private Movie convertToEntity(MovieDTO movieDTO) throws ParseException {
        return modelMapper.map(movieDTO, Movie.class);

    }

}
