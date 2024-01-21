package Test;

import Controller.AddObj;
import Controller.DataBase;
import Model.Inherited.Trader;
import Model.Worker.Worker;
import Service.DataInputHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


public class AddTraderEmptyTest {
    private DataInputHandler inputHandler;
    private DataBase<Worker> testDataBase;


    @Test
    public void test() {
        List<String> field = Arrays.asList("H", "78062598379", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "3333.0");
        inputHandler = Mockito.mock(DataInputHandler.class);
        testDataBase = new DataBase<>();
        TestMethod.checkTrader(field, testDataBase, inputHandler);
    }
}