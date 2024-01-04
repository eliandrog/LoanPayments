package com.example.loanpayments.FactoryLoan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


public class SimpleInterestLoan extends AbstractLoanImpl {
    private static final Logger logger = LoggerFactory.getLogger(SimpleInterestLoan.class);



    public SimpleInterestLoan( Integer loanLength, Double principal) {
        //TODO : make it use super fields
        super( InterestTypes.SIMPLE,loanLength,principal);

    }


    /**
     * Calculates the monthly repayment for a simple interest loan
     *
     *
     * @param loanLength
     * @param principal
     * @return monthly repayment rounded to 1 decimal place for loanlength > 1 (month) or full amount
     */


    @Override
    public BigDecimal calculateMonthlyRepayment(Integer loanLength, Double principal) {


            logger.info("Trying to calculate monthly payments... interest = "+interest);
            totalOwed = principal * (1 + (interest/100 )) ;

            logger.info("Calculating monthly payments ...");

            BigDecimal monthlyRepayment = BigDecimal.valueOf((principal * (1 + (interest/100 )) )/ loanLength);

            if (loanLength > 1 ){

                monthlyRepayment = BigDecimal.valueOf(Double.parseDouble(String.format("%.1f", monthlyRepayment)));

            }
            this.monthlyRepayment = monthlyRepayment;
            logger.info("Calculation completed ...");
            return monthlyRepayment;



    }


    @Override
    public BigDecimal lastMonthlyRepayment() throws NullPointerException{
            /** totalowed - (monthly payments * (loanlength -1) )   -> gives the last payment value hence loanlength - 1 **/
            BigDecimal lastMonthlyRepayment = BigDecimal.valueOf(totalOwed).subtract(monthlyRepayment.multiply(BigDecimal.valueOf( loanLength - 1.0)));
            this.lastMonthlyRepayment = lastMonthlyRepayment;


        return lastMonthlyRepayment;
    }

}
