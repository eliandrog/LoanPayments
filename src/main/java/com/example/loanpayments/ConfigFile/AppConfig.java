package com.example.loanpayments.ConfigFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class AppConfig {


    private final double interest;

    public AppConfig(@Value("${Loan.Interest}") double interest) {
        this.interest = interest;
    }






}
