package com.example.loanpayments.FactoryLoan;

import java.math.BigDecimal;

public interface Loan {

     BigDecimal calculateMonthlyRepayment( Integer loanLength, Double principal) throws Exception;


}
