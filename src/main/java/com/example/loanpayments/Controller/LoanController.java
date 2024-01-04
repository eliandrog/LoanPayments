package com.example.loanpayments.Controller;

import com.example.loanpayments.DTO.LoanDTO;
import com.example.loanpayments.FactoryLoan.AbstractLoanImpl;
import com.example.loanpayments.Service.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("api/loan")
public class LoanController {
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;


    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;

    }






    @PostMapping("/calculateMontlyPayment")
    public ResponseEntity<String> calculateMontlyPayment(@Valid @RequestBody LoanDTO loan) {



                AbstractLoanImpl abstractLoan = loanService.processAbstractLoan(loan);
                BigDecimal monthlyPayment = abstractLoan.calculateMonthlyRepayment(abstractLoan.getLoanLength(), abstractLoan.getPrincipal());
                logger.info("Monthly payment = " + monthlyPayment);

                return ResponseEntity.ok(
                        "Your monthly payment is: " + abstractLoan.getMonthlyRepayment() + " and your last payment = " + abstractLoan.lastMonthlyRepayment() +
                                "\nFor a total of : " + abstractLoan.getLoanLength() + " months" +
                                "\nTotal owed =  " + abstractLoan.getTotalOwed());

    }
}
