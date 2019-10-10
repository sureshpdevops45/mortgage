package com.hcl.mortgage.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.mortgage.dto.LoanInfoRequestDto;
import com.hcl.mortgage.service.LoanService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class LoanControllerTest {

	@InjectMocks
	LoanController loanController;
	
	@Mock
	LoanService loanServcie;
	
	LoanInfoRequestDto loanInfoRequestDto;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
		loanInfoRequestDto.setAnnualSalary(100000D);
		loanInfoRequestDto.setCustomerId(1);
		loanInfoRequestDto.setLoanAmount(500000D);
		loanInfoRequestDto.setLoanTenure(2F);
		loanInfoRequestDto.setPropertyType("Home");
		loanInfoRequestDto.setPropertyValue(400000D);
	}
	
	@Test
	public void testInfo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/loanInfo").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(loanInfoRequestDto)))
				.andExpect(status().isCreated());
	}
	
	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
