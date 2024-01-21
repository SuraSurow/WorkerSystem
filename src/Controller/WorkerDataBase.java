package Controller;


import Model.Worker.Worker;

import java.util.*;

public class WorkerDataBase<T extends Worker> {
    private Map<String, T> workerByPesel;


    private  Object lastAddedObject;

    public WorkerDataBase() {
        this.workerByPesel = new HashMap<>();
    }

    public  Object getLastAddedObject() {
        return lastAddedObject;
    }
    public  void setLastAddedObject( Worker worker)
    {
        lastAddedObject = worker;
    }

    synchronized public boolean addWorker(T obj) {
        if (workerByPesel.containsKey(obj.getPesel())) {
            System.out.println("Pracownik o podanym PESEL już istnieje w bazie.");
            return false;
        } else {
            workerByPesel.put(obj.getPesel(), obj);
            System.out.println("Pracownik dodany do bazy.");
            return true;
        }
    }

    synchronized public void setWorkerByPesel(Map<String, T> workerByPesel) {
        this.workerByPesel = workerByPesel;
    }



    public Map<String, T> getWorkerByPesel() {
        return workerByPesel;
    }

    synchronized public void deleteWorker(T obj){
        workerByPesel.remove(obj.getPesel());
    }


    public T getWorkerByPesel(String pesel) {
        return workerByPesel.get(pesel);
    }

    public Set<String> getAllPesels() {
        return workerByPesel.keySet();
    }

    public int getSize() {
        return workerByPesel.size();
    }

    public boolean isEmpty() {
        if (workerByPesel.isEmpty()) return true;
        return false;
    }




}
