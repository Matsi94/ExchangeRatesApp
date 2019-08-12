package app;

import exception.NoSuchOptionException;
import io.ConsolePrinter;
import io.DataDownload;
import io.DataReader;

import java.util.InputMismatchException;

public class AppControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader reader = new DataReader(printer);
    private DataDownload dataDownload = new DataDownload();

    void optionLoop(){
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option){
                case CHECK_BUY_RATE:
                    checkBuyRate();
                    break;
                case CHECK_SELL_RATE:
                    checkSellRate();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printerLine("There is no such an option, please enter option number again.");
                    break;
            }

        } while (option != Option.EXIT);
    }

    private void checkSellRate() {
        Currency currency;
        printCurrency();
        currency = getCurrency();
        try{
            dataDownload.getSellRate(currency);
        } catch (Exception e){
            printer.printerLine(e.getMessage());
        }
    }

    private void exit() {
        reader.close();
        printer.printerLine("Turning off, goodbye.");
    }

    private void checkBuyRate() {
        Currency currency;
        printCurrency();
        currency = getCurrency();
        try{
            dataDownload.getBuyRate(currency);
        } catch (Exception e){
            printer.printerLine(e.getMessage());
        }
    }

    private Currency getCurrency() {
        boolean currencyOk = false;
        Currency currency = null;
        while (!currencyOk){
            try {
                currency = Currency.createFromInt(reader.getInt());
                currencyOk = true;
            } catch (NoSuchOptionException e){
                printer.printerLine(e.getMessage() + ", enter again.");
            } catch (InputMismatchException e){
                printer.printerLine("Entered value is not a number, please try again.");
            }
        }
        return currency;
    }

    private void printCurrency() {
        for (Currency currency: Currency.values()) {
            printer.printerLine(currency.toString());
        }
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk){
            try {
                option = Option.createFromInt(reader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e){
                printer.printerLine(e.getMessage() + ", enter again.");
            } catch (InputMismatchException e){
                printer.printerLine("Entered value is not a number, please try again.");
            }
        }
        return option;
    }

    private void printOptions() {
        for (Option options : Option.values()){
            printer.printerLine(options.toString());
        }
    }
}
