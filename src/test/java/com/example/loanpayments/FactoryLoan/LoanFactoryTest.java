package com.example.loanpayments.FactoryLoan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LoanFactoryTest {

    @Test
    void createLoan_SimpleInterestLoanSuccessfully() {

        LoanFactory loanFactoryDays = new LoanFactory();
        AbstractLoanImpl abstractLoanDays = (AbstractLoanImpl) loanFactoryDays.createLoan(10.0, InterestTypes.SIMPLE, LoanLengthType.DAYS, 365, 1000);
        LoanFactory loanFactoryMonth = new LoanFactory();
        AbstractLoanImpl abstractLoanMonth = (AbstractLoanImpl) loanFactoryMonth.createLoan(10.0, InterestTypes.SIMPLE, LoanLengthType.MONTHS, 365, 1000);
        LoanFactory loanFactoryYears = new LoanFactory();
        AbstractLoanImpl abstractLoanYears = (AbstractLoanImpl) loanFactoryYears.createLoan(10.0, InterestTypes.SIMPLE, LoanLengthType.YEARS,365 , 1000);

        assertTrue( abstractLoanDays instanceof SimpleInterestLoan);
        assertTrue( abstractLoanMonth instanceof SimpleInterestLoan);
        assertTrue( abstractLoanYears instanceof SimpleInterestLoan);



    }


    @Test
    void convertLoanLengthToMonths() {



LoanFactory loanFactory = new LoanFactory();
        Integer loanLengthInMonthsDays = loanFactory.convertLoanLengthToMonths(LoanLengthType.DAYS, 365);
        Integer loanLengthInMonthsDays2 = loanFactory.convertLoanLengthToMonths(LoanLengthType.DAYS, 360);
        Integer loanLengthInMonthsDays3 = loanFactory.convertLoanLengthToMonths(LoanLengthType.DAYS, 361);
        Integer loanLengthInMonthsMonths = loanFactory.convertLoanLengthToMonths(LoanLengthType.MONTHS, 12);
        Integer loanLengthInMonthsYears = loanFactory.convertLoanLengthToMonths(LoanLengthType.YEARS, 1);


        assertNotEquals(12,loanLengthInMonthsDays);
        assertEquals(12, loanLengthInMonthsMonths);
        assertEquals(12, loanLengthInMonthsYears);
        assertEquals(12, loanLengthInMonthsDays2);
        assertEquals(13, loanLengthInMonthsDays3);
    }
}