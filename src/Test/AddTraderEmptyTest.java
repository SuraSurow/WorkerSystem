package Test;

import Controller.DataBase;
import Model.Worker.Worker;
import Service.DataInputHandler;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;


public class AddTraderEmptyTest {
    private DataInputHandler inputHandler;
    private DataBase<Worker> testDataBase;


    @Test
    public void test() {
        List<String> field = Arrays.asList("H", "78062598379", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "3333.0");
        inputHandler = Mockito.mock(DataInputHandler.class);
        testDataBase = new DataBase<>();
        TestMethod.checkTraderADd(field, testDataBase, inputHandler);
    }
}