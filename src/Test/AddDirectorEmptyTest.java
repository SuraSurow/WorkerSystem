package Test;
import Controller.DataBase;
import Model.Worker.Worker;
import Service.DataInputHandler;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;


public class AddDirectorEmptyTest {
    private DataBase<Worker> dataBase;
    private DataInputHandler inputHandler;





    @Test
    public void test() {
        List<String> field = Arrays.asList("D", "78062598379", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "321123", "3333.0");
        dataBase = new DataBase<>();
        inputHandler = Mockito.mock(DataInputHandler.class);
        TestMethod.checkDirectorAdd(field,dataBase,inputHandler);
    }
}
