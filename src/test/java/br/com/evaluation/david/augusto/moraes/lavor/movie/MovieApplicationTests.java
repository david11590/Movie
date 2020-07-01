package br.com.evaluation.david.augusto.moraes.lavor.movie;

import br.com.evaluation.david.augusto.moraes.lavor.movie.dao.JdbcMovie;
import br.com.evaluation.david.augusto.moraes.lavor.movie.util.ValidateMovie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.web.servlet.MockMvc;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieApplicationTests
{

	@Autowired
	ValidateMovie ValidateMovie;

	@Autowired
	MockMvc mock;

	@Test
	public void testMockGetAllMovie()
			throws Exception {
		mock.perform(get("/movie")).andExpect(status().isAccepted());
	}

	@Test
	public void testMockGetMovie()
			throws Exception {
		mock.perform(get("/movie/CENSURADO")).andExpect(status().isAccepted());
	}

	@Test
	public void testMockGetMovie2()
			throws Exception {
		mock.perform(get("/movie/SEM_CENSURA")).andExpect(status().isAccepted());
	}

	@Test
	public void testMockPostMovie()
			throws Exception {
		mock.perform(
				post("/movie")
						.content("{\"nome\":\"O ultimo dos moicanosf\", \"dataDeLancamento\":\"20/01/03\", \"nivelDeCensura\":\"CENSURADO\", \"direcao\":\"fulanão fulanosvaldo\",\"Elenco\":[\"teste\",\"osvaldo cruz\"]}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}

	@Test
	public void testValidJdbcMovieConectionH2()
	{
		assertEquals(1,
				new JdbcTemplate(new EmbeddedDatabaseBuilder()
						.setType(EmbeddedDatabaseType.H2).build()).queryForObject(
						"SELECT 1 FROM DUAL",
						Integer.class
				)
		);
	}

	@Test
	void testValidRequestGetMovie()
	{
		ValidateMovie.validateGetMovie("CENSURADO");
	}

	@Test
	void testValidRequestGetMovie2()
	{
		ValidateMovie.validateGetMovie("SEM_CENSURA");
	}

	@Test
	public void testValidJdbcMovieFindMovie()
	{
		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
		JdbcMovie jdbcMovie = new JdbcMovie(new JdbcTemplate(dataSource));
		jdbcMovie.findMovie(null);

	}
}