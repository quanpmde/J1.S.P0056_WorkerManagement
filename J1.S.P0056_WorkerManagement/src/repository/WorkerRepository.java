package repository;

import common.Library;
import common.Validation;
import dataAccess.WorkerDao;
import java.util.Collections;
import java.util.List;
import model.Worker;

public class WorkerRepository implements IWorkerRepository {

    @Override
    public void addWorker(List<Worker> workers, Worker new_worker) {
        if (!WorkerDao.Instance().addWorker(workers, new_worker)) {
            System.out.println("ID exist");
            return;
        }
        System.out.println("Successful");
    }

    @Override
    public void changeSalary(List<Worker> workers, int type) {
        if (workers.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        Collections.sort(workers, new Library());
        Validation valid = new Validation();
        String iD = valid.inputString("Enter ID");
        int location = valid.checkID(workers, iD);
        if (location == -1) {
            System.out.println("ID not exist");
            return;
        }
        if (!WorkerDao.Instance().changeSalary(workers, location, type)) {
            System.out.println("Change salary exceed original salary!");
            return;
        }
        System.out.println("Successful");
    }

    @Override
    public void display(List<Worker> workers) {
        if (workers.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        WorkerDao.Instance().display(workers);
    }
}