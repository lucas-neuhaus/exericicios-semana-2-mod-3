package com.senai.ex2semana2mod3;

import com.senai.ex2semana2mod3.model.Time;
import com.senai.ex2semana2mod3.service.TimeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class TimeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TimeService timeService;

	@Test
	void criarTime_deveRetornarTimeCriado() throws Exception {
		Time timeMock = new Time();
		timeMock.setId(1L);
		timeMock.setNome("São Paulo Futebol Clube");
		timeMock.setTecnico("Dorival Júnior");
		timeMock.setTitulos(42);

		when(timeService.salvarTime(Mockito.any(Time.class))).thenReturn(timeMock);

		String requestBody = "{\"nome\":\"São Paulo Futebol Clube\",\"tecnico\":\"Dorival Júnior\",\"titulos\":42}";

		mockMvc.perform(MockMvcRequestBuilders.post("/time")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("São Paulo Futebol Clube"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.tecnico").value("Dorival Júnior"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.titulos").value(42));
	}
	@Test
	void buscarTimePorNome_deveRetornarTimesComNomeCorrespondente() throws Exception {
		Time time1 = new Time();
		time1.setId(1L);
		time1.setNome("São Paulo Futebol Clube");
		time1.setTecnico("Dorival Júnior");
		time1.setTitulos(42);

		Time time2 = new Time();
		time2.setId(2L);
		time2.setNome("Palmeiras");
		time2.setTecnico("Abel Ferreira");
		time2.setTitulos(37);

		List<Time> times = Arrays.asList(time1, time2);

		when(timeService.buscarTimesPorNome(Mockito.anyString())).thenReturn(times);

		mockMvc.perform(MockMvcRequestBuilders.get("/time/buscarPorNome")
						.param("nome", "São Paulo"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("São Paulo Futebol Clube"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].tecnico").value("Dorival Júnior"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].titulos").value(42))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Palmeiras"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].tecnico").value("Abel Ferreira"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].titulos").value(37));
	}
}