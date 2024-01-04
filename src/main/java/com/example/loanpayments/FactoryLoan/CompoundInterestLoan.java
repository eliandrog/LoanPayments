package com.example.loanpayments.FactoryLoan;

import java.math.BigDecimal;

public class CompoundInterestLoan extends AbstractLoanImpl {


    public CompoundInterestLoan( Integer loanLength, double principal) {
        super( InterestTypes.COMPOUND,loanLength,principal);
    }

    @Override
    public BigDecimal calculateMonthlyRepayment(Integer loanLength, Double principal) {
        return null;
    }

    @Override
    public BigDecimal lastMonthlyRepayment() {
        return null;
    }
}
