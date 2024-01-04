package com.example.loanpayments.FactoryLoan;

import org.springframework.stereotype.Component;


//@AllArgsConstructor
@Component
public class LoanFactory {



    public Loan createLoan(Double interest,InterestTypes interestType, LoanLengthType loanLengthType , int loanLength, double principal){
        if (interestType == null || interestType.describeConstable() == null || interestType.describeConstable().isEmpty()) {       //avoid null pointer exception in this order
            throw new IllegalArgumentException("Interest type cannot be null");
        }
        int loanLengthInMonths = convertLoanLengthToMonths(loanLengthType, loanLength);
        AbstractLoanImpl abstractLoan = switch (interestType) {

            case SIMPLE -> new SimpleInterestLoan( loanLengthInMonths, principal);
            //case COMPOUND -> new CompoundInterestLoan( loanLengthInMonths, principal);
            default -> throw new IllegalStateException("Unexpected value: " + interestType);
        };

        /**
         * Overwrites/sets the interest rate for the loan since its optional on the DTO
         * Default is retrieved from the application.properties file -> AppConfig (injection) ->on instantiation of the LoanService
         */
        abstractLoan.setInterest(interest);
        abstractLoan.setLoanEndDate(abstractLoan.getLoanStartDate().plusMonths(loanLengthInMonths)); //new line added

        return abstractLoan;
    }



    public Integer convertLoanLengthToMonths(LoanLengthType loanLengthType, int loanLength){
        int loanLengthConvertedToMonths= switch (loanLengthType) {
            case DAYS ->   (int) Math.ceil(loanLength / 30.0) ; //round up to nearest month
            case MONTHS -> loanLength;
            case YEARS ->  loanLength * 12;
        };
        return loanLengthConvertedToMonths ;
    }



}
