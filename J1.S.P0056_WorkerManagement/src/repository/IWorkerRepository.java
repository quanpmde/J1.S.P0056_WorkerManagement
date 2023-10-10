package repository;

import java.util.List;
import model.Worker;

/**
 *
 * @author GoldCandy
 */
public interface IWorkerRepository {

    void addWorker(List<Worker> workers, Worker new_worker);

    void changeSalary(List<Worker> workers, int type);

    void display(List<Worker> workers);
    
}