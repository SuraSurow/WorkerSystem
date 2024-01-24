package Test;

import Controller.AddObj;
import Controller.DataBase;
import Model.Inherited.Director;
import Model.Worker.Worker;
import Service.DataInputHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class DeleteDirectorFullTest {

    private DataBase<Worker> testDataBase;
    private DataInputHandler inputHandler;

    private List<List<String>> dataObj = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList("D", "70041579919", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0","312", "3333.0"),
                    Arrays.asList("D", "75062848169", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0","312", "3333.0"),
                    Arrays.asList("D", "80041735558", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0","312", "3333.0"),
                    Arrays.asList("D", "57042748586", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0","312", "3333.0"),
                    Arrays.asList("D", "84111982514", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "312","3333.0")
            )
    );

    public DeleteDirectorFullTest()
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
                    .thenReturn(field.get(8))
                    .thenReturn(field.get(9));

            Assertions.assertTrue(dodawacz.addWorker());
        }
    }

    @Test
    public void test() {
        List<String> field = Arrays.asList("D", "57062166214", "TO", "chce", "Usunac", "2213.2", "555555555", "141414.0", "999392", "44444.0");
        Director wannaDelete = new Director( field.get(1), field.get(2), field.get(3), field.get(4), field.get(5), field.get(6), field.get(7),field.get(8), field.get(9));
        inputHandler = Mockito.mock(DataInputHandler.class);
        TestMethod.checkDirectorAdd(field,testDataBase,inputHandler);
        testDataBase.deleteWorker(wannaDelete);
        String[] keyArray = testDataBase.getAllPesels().toArray(new String[0]);
        for(int i = 0 ; i < testDataBase.getSize() ; i ++)
        {
            Assertions.assertNotEquals(wannaDelete,testDataBase.getWorkerByPesel(keyArray[i]));
        }
    }
}
