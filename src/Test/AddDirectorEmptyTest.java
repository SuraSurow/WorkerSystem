package Test;
import Controller.WorkerAdd;
import Controller.WorkerDataBase;
import Model.Inherited.Director;
import Model.Worker.Worker;
import Service.DataInputHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


public class AddDirectorEmptyTest {
    private WorkerDataBase<Worker> dataBase;
    private DataInputHandler inputHandler;





    @Test
    public void test() {
        List<String> field = Arrays.asList("D", "78062598379", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0", "321123", "3333.0");
        dataBase = new WorkerDataBase<>();
        inputHandler = Mockito.mock(DataInputHandler.class);
        Add.checkDirector(field,dataBase,inputHandler);
    }
}
