package app;

import exception.NoSuchOptionException;

public enum Currency {
    EUR(1, "Euro"),
    USD(2, "US Dollar"),
    AUD(3, "Australian Dollar"),
    CAD(4, "Canadian Dollar"),
    CHF(5, "Swiss Franc"),
    GBP(6, "Pound Sterling"),
    JPY(7, "Yen");

    private int value;
    private String description;

    Currency(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return value + ". " + description;
    }

    static Currency createFromInt(int number) throws NoSuchOptionException {
        try{
            return Currency.values()[number-1];
        } catch (IndexOutOfBoundsException e){
            throw new NoSuchOptionException("There is option id: " + number);
        }
    }
}
