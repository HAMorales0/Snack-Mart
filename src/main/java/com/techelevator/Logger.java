package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private final String LOG_PATH = "Log.txt";
    private File logFile;

    PrintWriter log;
    File transactionLog;
    {
        try {
            transactionLog = new File(LOG_PATH);
            log = new PrintWriter(transactionLog);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd//yyyy HH:mm:ss a");
    LocalDateTime dateTime = LocalDateTime.now();
    String dateTimeString = dateTime.format(formatter);

    public void logDeposits(BigDecimal deposit, BigDecimal balance) {
        log.println(dateTimeString + " Money deposit: $" + deposit + " $" + balance);
    }

    public void logPurchases(String code, String name, BigDecimal cost, BigDecimal balance) {
        log.println(dateTimeString + " Purchase: " + code + " " + name + " $" + cost + " $" + balance);
    }

}
