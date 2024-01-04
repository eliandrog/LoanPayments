package com.example.loanpayments.FactoryLoan;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimpleInterestLoanTest {
    Integer LOAN_LENGTH_IN_MONTHS = 13;
    Double PRINCIPAL = 1000.0;

    @Test
    void whencalculateMonthlyRepayment_thenSuccessful() {


        SimpleInterestLoan simpleInterestLoan = new SimpleInterestLoan( LOAN_LENGTH_IN_MONTHS,PRINCIPAL);
        simpleInterestLoan.setInterest(10.0);

        BigDecimal monthlyRepayment = simpleInterestLoan.calculateMonthlyRepayment(LOAN_LENGTH_IN_MONTHS,PRINCIPAL);
        assertEquals(new BigDecimal("84.6"), monthlyRepayment);



    }

    @Test
    void lastMonthlyRepayment() {


        SimpleInterestLoan simpleInterestLoan = mock(SimpleInterestLoan.class, withSettings().defaultAnswer(CALLS_REAL_METHODS));
        AbstractLoanImpl abstractLoan =simpleInterestLoan ;


        abstractLoan.monthlyRepayment=new BigDecimal("84.6");
        abstractLoan.loanLength= LOAN_LENGTH_IN_MONTHS;
        abstractLoan.totalOwed= (1100.0);
        abstractLoan.interest=10.0;
        //abstractLoan.monthlyRepayment = BigDecimal.valueOf(84.6);



        //  when(abstractLoan.loanLength).thenReturn(13);
        BigDecimal lastMonthlyRepayment = simpleInterestLoan.lastMonthlyRepayment();
        assertEquals(new BigDecimal("84.80"), lastMonthlyRepayment);




    }
}