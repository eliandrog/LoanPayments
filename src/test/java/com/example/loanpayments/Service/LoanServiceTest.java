package com.example.loanpayments.Service;

import com.example.loanpayments.DTO.LoanDTO;
import com.example.loanpayments.FactoryLoan.*;
import com.example.loanpayments.FactoryLoan.InterestTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private  LoanFactory loanFactory;
//    @Mock
//    private  AppConfig appConfig;
//
//    @InjectMocks
//    private LoanService loanService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
       // when(appConfig.getInterest()).thenReturn(9.3);
    }

    @Test
    void processAbstractLoan_successfull() {




        MockitoAnnotations.openMocks(this);


        LoanDTO loanDTO = new LoanDTO(InterestTypes.SIMPLE, LoanLengthType.DAYS, 365, 1000.0);
        loanDTO.setInterest(10.0);

        // Mock behavior for loanFactory.createLoan() method
        when(loanFactory.createLoan(eq(10.0), eq(InterestTypes.SIMPLE), eq(LoanLengthType.DAYS), eq(365), eq(1000.0)))
                .thenReturn(new SimpleInterestLoan(365, 1000.0));

        // Call the method being tested
        AbstractLoanImpl loanSimple = (AbstractLoanImpl) loanFactory.createLoan(10.0, InterestTypes.SIMPLE, LoanLengthType.DAYS, 365, 1000.0);


        verify(loanFactory).createLoan(eq(10.0), eq(InterestTypes.SIMPLE), eq(LoanLengthType.DAYS), eq(365), eq(1000.0));

        assertNotNull(loanSimple);
        assertEquals(InterestTypes.SIMPLE, loanSimple.getLoanInterestType());


    }

    @Test
    void setInterestForLoan() {
    }
}