package Test;

import Controller.AddObj;
import Controller.DataBase;
import Model.Worker.Worker;
import Service.DataInputHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class AddTraderFullTest {

    private DataBase<Worker> testDataBase;
    private DataInputHandler inputHandler;

    private List<List<String>> dataObj = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList("H", "70041579919", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "3333.0"),
                    Arrays.asList("H", "75062848169", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "3333.0"),
                    Arrays.asList("H", "80041735558", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "3333.0"),
                    Arrays.asList("H", "57042748586", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "3333.0"),
                    Arrays.asList("H", "84111982514", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "3333.0")
            )
    );

    public AddTraderFullTest()
    {
        testDataBase = new DataBase<>();
        inputHandler = Mockito.mock(DataInputHandler.class);
        AddObj dodawacz = new AddObj(testDataBase, inputHandler);
        for(int i = 0 ; i < dataObj.size() ; i ++)
        {
            List<String> field = dataObj.get(i);
            when(inputHandler.getUserInput())
                    .thenReturn(field.get(0))
                    .thenReturn(field.get(1))
                    .thenReturn(field.get(2))
                    .thenReturn(field.get(3))
                    .thenReturn(field.get(4))
                    .thenReturn(field.get(5))
                    .thenReturn(field.get(6))
                    .thenReturn(field.get(7))
                    .thenReturn(field.get(8));

            Assertions.assertTrue(dodawacz.addWorker());
        }
    }

    @Test
    public void test() {
        List<String> field = Arrays.asList("H", "59061818475", "AHA", "TO", "NIEWIARYGODNE", "1111.0", "232232323", "99999.0",  "1123.0");
        inputHandler = Mockito.mock(DataInputHandler.class);
        TestMethod.checkTraderADd(field,testDataBase,inputHandler);
    }
}