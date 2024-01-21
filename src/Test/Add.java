package Test;

import Controller.WorkerAdd;
import Controller.WorkerDataBase;
import Model.Inherited.Director;
import Model.Worker.Worker;
import Service.DataInputHandler;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.mockito.Mockito.when;

public class Add {
    public static void checkDirector(List<String> field, WorkerDataBase<Worker> data, DataInputHandler inputHandler)
    {
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
        WorkerAdd dodawacz = new WorkerAdd(data, inputHandler);
        Assertions.assertTrue(dodawacz.addWorker());
        Director obiekt = (Director) data.getLastAddedObject();
        Assertions.assertEquals(field.get(1), obiekt.getPesel());
        Assertions.assertEquals(field.get(2), obiekt.getName());
        Assertions.assertEquals(field.get(3), obiekt.getSurname());
        Assertions.assertEquals(field.get(4), obiekt.getPosition());
        Assertions.assertEquals(field.get(5), String.valueOf(obiekt.getSalary()));
        Assertions.assertEquals(field.get(6), String.valueOf(obiekt.getPhoneNumber()));
        Assertions.assertEquals(field.get(7), String.valueOf(obiekt.getServiceAllowance()));
        Assertions.assertEquals(field.get(8), String.valueOf(obiekt.getServiceCard()));
        Assertions.assertEquals(field.get(9), String.valueOf(obiekt.getLimitCosts()));
    }
}
