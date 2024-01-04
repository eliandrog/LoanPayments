package com.example.loanpayments.FactoryLoan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public abstract class AbstractLoanImpl implements Loan {


    protected Double interest;

    protected InterestTypes loanInterestType;
    protected LoanLengthType loanLengthType;

    protected int loanLength;

    protected Double principal;

    protected LocalDateTime loanStartDate;
    protected LocalDateTime loanEndDate;

    @Setter(AccessLevel.NONE)
    protected BigDecimal monthlyRepayment;
    @Setter(AccessLevel.NONE)
    protected BigDecimal lastMonthlyRepayment;
    @Setter(AccessLevel.NONE)
    protected Double totalOwed;



    //Loans are taken on the day with a start day of Today +1 :00:00:00
    public AbstractLoanImpl(InterestTypes loanInterestType, Integer loanLength, Double principal) {

        this.loanInterestType = loanInterestType;
        //this.loanLengthType = loanLengthType;
        this.loanLength = loanLength;
        this.principal = principal;
        loanStartDate = LocalDateTime.now().plusDays(1);
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }


    public abstract BigDecimal calculateMonthlyRepayment(Integer loanLength, Double principal) ;//throws Exception;


    public abstract BigDecimal lastMonthlyRepayment();
}
