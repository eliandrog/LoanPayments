package com.example.loanpayments.Controller;

import com.example.loanpayments.ConfigFile.AppConfig;
import com.example.loanpayments.DTO.LoanDTO;
import com.example.loanpayments.FactoryLoan.AbstractLoanImpl;
import com.example.loanpayments.FactoryLoan.LoanFactory;
import com.example.loanpayments.FactoryLoan.LoanLengthType;
import com.example.loanpayments.FactoryLoan.SimpleInterestLoan;
import com.example.loanpayments.FactoryLoan.InterestTypes;
import com.example.loanpayments.Service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;//:jackson-datatype-jsr310


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;



@WebMvcTest(LoanController.class)
class LoanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AppConfig appConfig;

    @MockBean
    LoanFactory loanFactory;

    @MockBean
    private LoanService loanService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void calculateMontlyPayment() throws Exception {
        // Create a LoanDTO object for testing
        LoanDTO loanDTO = new LoanDTO(InterestTypes.SIMPLE, LoanLengthType.DAYS, 365, 1000.0);
        loanDTO.setInterest(10.0); // Set interest value


        AbstractLoanImpl mockLoan = new SimpleInterestLoan(13, 1000.0);
        mockLoan.setInterest(10.0);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());


        when(loanService.processAbstractLoan(any(LoanDTO.class))).thenReturn(mockLoan);

        //
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/loan/calculateMontlyPayment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(loanDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        assertThat(responseString).
                containsPattern("Your monthly payment is: \\d.*").
                containsPattern("For a total of : \\d.*").
                containsPattern("Total owed =.*\\d.*");
    }


}