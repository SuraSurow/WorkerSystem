package MODEL.Worker;

import java.util.*;

public class WorkerDataBase<T extends Worker> {
    private Map<String, T> workerByPesel;

    private List<String> peselList;


    public WorkerDataBase() {
        this.workerByPesel = new HashMap<>();
        this.peselList = new ArrayList<>();
    }

    public void addWorker(T obj) {
        if (workerByPesel.containsKey(obj.getPesel())) {
            System.out.println("Pracownik o podanym PESEL już istnieje w bazie.");
        } else {
            workerByPesel.put(obj.getPesel(), obj);
            System.out.println("Pracownik dodany do bazy.");
        }
    }
    public void deleteWorker(T obj){
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

    public Map<String, T> getWorkerByPesel() {
        return workerByPesel;
    }

    public List<String> getPeselList() {
        return peselList;
    }



    public List<Worker> getAllWorkers() {
        return new ArrayList<>(workerByPesel.values());
    }

    public int getWorkersCount() {
        return workerByPesel.size();
    }

    public int getPeselListSize() {
        return peselList.size();
    }

    public boolean isEmpty() {
        if (workerByPesel.isEmpty()) return true;
        return false;
    }
}
