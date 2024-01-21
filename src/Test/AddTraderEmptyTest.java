package Test;

import Controller.WorkerAdd;
import Controller.WorkerDataBase;
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
    private WorkerDataBase<Worker> testDataBase;


    @Test
    public void test() {
        List<String> field = Arrays.asList("H", "78062598379", "Uga", "Buga", "Magik", "4231.0", "997997997", "1000.0",  "3333.0");
        inputHandler = Mockito.mock(DataInputHandler.class);
        testDataBase = new WorkerDataBase<>();
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

        WorkerAdd dodawacz = new WorkerAdd(testDataBase, inputHandler);
        Assertions.assertTrue(dodawacz.addWorker());
        Trader obiekt = (Trader) testDataBase.getLastAddedObject();
        Assertions.assertEquals(field.get(1), obiekt.getPesel());
        Assertions.assertEquals(field.get(2), obiekt.getName());
        Assertions.assertEquals(field.get(3), obiekt.getSurname());
        Assertions.assertEquals(field.get(4), obiekt.getPosition());
        Assertions.assertEquals(field.get(5), String.valueOf(obiekt.getSalary()));
        Assertions.assertEquals(field.get(6), String.valueOf(obiekt.getPhoneNumber()));
        Assertions.assertEquals(field.get(7), String.valueOf(obiekt.getCommissionRate()));
        Assertions.assertEquals(field.get(8), String.valueOf(obiekt.getLimitCommission()));
    }
}