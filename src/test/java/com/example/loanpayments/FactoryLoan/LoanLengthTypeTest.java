package com.example.loanpayments.FactoryLoan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanLengthTypeTest {

    @Test
    void values() {
        assertNotNull(LoanLengthType.MONTHS);
        assertNotNull(LoanLengthType.YEARS);
        assertNotNull(LoanLengthType.DAYS);

        // Test enum constant names
        assertEquals("DAYS", LoanLengthType.DAYS.name());
        assertEquals("MONTHS", LoanLengthType.MONTHS.name());
        assertEquals("YEARS", LoanLengthType.YEARS.name());


        // Test enum constant order (ordinal)
        assertEquals(1, LoanLengthType.MONTHS.ordinal());
        assertEquals(2, LoanLengthType.YEARS.ordinal());
        assertEquals(0, LoanLengthType.DAYS.ordinal());

    }
}