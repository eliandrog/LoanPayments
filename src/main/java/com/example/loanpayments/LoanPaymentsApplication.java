package com.example.loanpayments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.loanpayments.ConfigFile"})
public class LoanPaymentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanPaymentsApplication.class, args);

    }


}
