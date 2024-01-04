package com.example.loanpayments.DTO;

import com.example.loanpayments.FactoryLoan.LoanLengthType;
import com.example.loanpayments.FactoryLoan.InterestTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.Getter;



import java.time.LocalDateTime;
@Getter
public class LoanDTO {



        @Positive(message = "Interest must be a positive number")
        protected Double interest;

        @NotNull(message = "Loan Interest Type cannot be null")
        @JsonDeserialize()
        protected InterestTypes loanInterestType;

        @NotNull(message = "Loan Length Type cannot be null")
        protected LoanLengthType loanLengthType;

        @NotNull(message = "Loan Length cannot be null")
        @Min(value = 1, message = "Loan Length must be greater than 0")
        protected Integer loanLength;

        @NotNull(message = "Principal cannot be null")
        protected Double principal;

        @JsonProperty
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        protected LocalDateTime loanStartDate;


        protected  LocalDateTime loanEndDate; //=  LocalDateTime.now().plusDays(1);


        //Loans are taken on the day with a start day of Today +1 :00:00:00
        public LoanDTO(InterestTypes loanInterestType, LoanLengthType loanLengthType, Integer loanLength, Double principal) {

            this.loanInterestType = loanInterestType;
            this.loanLengthType = loanLengthType;
            this.loanLength = loanLength;
            this.principal = principal;


            loanStartDate = LocalDateTime.now().plusDays(1);
            //loanEndDate = loanStartDate;


    }
    public void setInterest(Double interest) {
        this.interest = interest;
    }

}
