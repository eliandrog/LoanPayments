package com.example.loanpayments.Service;


import com.example.loanpayments.ConfigFile.AppConfig;
import com.example.loanpayments.DTO.LoanDTO;
import com.example.loanpayments.FactoryLoan.AbstractLoanImpl;
import com.example.loanpayments.FactoryLoan.LoanFactory;
import com.example.loanpayments.Controller.LoanController;
import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Scope("prototype")
@Service
public class LoanService {
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final AppConfig appConfig;
    private Double interest;
    private final LoanFactory loanFactory;






    @Autowired
    public LoanService(AppConfig appConfig, LoanFactory loanFactory){

        this.appConfig = appConfig;
        this.loanFactory = loanFactory;


    }




    public AbstractLoanImpl processAbstractLoan( LoanDTO loanDTO){
        logger.info("interest LoadDTO in Service = "+interest);
        setInterestForLoan(loanDTO.getInterest());
        AbstractLoanImpl loan = (AbstractLoanImpl) loanFactory.createLoan(interest, loanDTO.getLoanInterestType(), loanDTO.getLoanLengthType(), loanDTO.getLoanLength(), loanDTO.getPrincipal());

        return loan;

    }

    public void setInterestForLoan(Double loanDTOInterest) {
        if (loanDTOInterest != null){
            interest = loanDTOInterest;
            logger.info("LoanDTO update default interest to  = "+loanDTOInterest+"%");

        }else {
            interest = appConfig.getInterest();
            logger.info("interest AppConfig in Service = "+interest);

        }
    }


}
