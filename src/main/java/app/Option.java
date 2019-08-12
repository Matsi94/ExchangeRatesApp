package app;

import exception.NoSuchOptionException;

public enum Option {
    CHECK_BUY_RATE(1, "Check buy rate."),
    CHECK_SELL_RATE(2, "Check sell rate."),
    EXIT(3, "Exit.");

    private int value;
    private String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return value + ". " + description;
    }

    static Option createFromInt(int number) throws NoSuchOptionException {
        try{
            return Option.values()[number-1];
        } catch (IndexOutOfBoundsException e){
            throw new NoSuchOptionException("There is option id: " + number);
        }
    }
}
