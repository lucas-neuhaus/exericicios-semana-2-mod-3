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
}