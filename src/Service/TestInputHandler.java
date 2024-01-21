package Service;

import Service.DataInputHandler;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TestInputHandler extends DataInputHandler {
    private Iterator<String> inputIterator;
    private int invocationCountUpperCase;
    private int invocationCount;

    public TestInputHandler(Scanner scanner, List<String> testInputs) {
        super(scanner);
        this.inputIterator = testInputs.iterator();
        this.invocationCountUpperCase = 0;
        this.invocationCount = 0;
    }

    @Override
    public String getUserInputUpperCase() {
        invocationCountUpperCase++;
        return inputIterator.next().toUpperCase();
    }

    @Override
    public String getUserInput() {
        invocationCount++;
        return inputIterator.next();
    }

    public int getInvocationCountUpperCase() {
        return invocationCountUpperCase;
    }

    public int getInvocationCount() {
        return invocationCount;
    }
}
