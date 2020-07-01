package br.com.evaluation.david.augusto.moraes.lavor.movie.util;

import br.com.evaluation.david.augusto.moraes.lavor.movie.dao.JdbcMovie;
import br.com.evaluation.david.augusto.moraes.lavor.movie.dto.Movie;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import java.security.InvalidParameterException;

@Component
public class ValidateMovie
{
    final JdbcMovie jdbcMovie;

    public ValidateMovie(JdbcMovie jdbcMovie)
    {
        this.jdbcMovie = jdbcMovie;
    }

   public void validateRequestMovie(Movie requestMovie)
           throws DuplicateKeyException,InvalidParameterException,NullPointerException
    {
        if(jdbcMovie.findMoovieNameExistsBoolean(requestMovie.getName()))
        {
            throw new DuplicateKeyException("Action not allowed, the movie already exists in the database.");
        }

        if(requestMovie.getCast()==null
           ||!(requestMovie.getCast().size()>=1
                && requestMovie.getCast().size() <=10))
        {
            throw new InvalidParameterException("Action not allowed, The Movie's cast must be provided and cannot exceed 10 actors.");
        }

        if(!(requestMovie.getCensorship().equals("CENSURADO")||
                requestMovie.getCensorship().equals("SEM_CENSURA")))
        {
            throw new InvalidParameterException("action not allowed, for censorship the allowed values are CENSURADO or SEM_CENSURA.");
        }
    }

    public void validateGetMovie(String censorship)
            throws InvalidParameterException
    {
        if(!(censorship.equals("CENSURADO")
                ||censorship.equals("SEM_CENSURA")))
        {
            throw new InvalidParameterException("action not allowed, for censorship the allowed values are CENSURADO or SEM_CENSURA.");
        }
    }
}
