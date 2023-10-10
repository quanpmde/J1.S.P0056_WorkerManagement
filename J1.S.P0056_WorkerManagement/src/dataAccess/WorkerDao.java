package dataAccess;

import common.Library;
import common.Validation;
import java.util.Collections;
import java.util.List;
import model.Worker;

/**
 *
 * @author GoldCandy
 */
public class WorkerDao {

    private static WorkerDao instance = null;
    private Validation valid = new Validation();

    public static WorkerDao Instance() {
        if (instance == null) {
            synchronized (WorkerDao.class) {
                if (instance == null) {
                    instance = new WorkerDao();
                }
            }
        }
        return instance;
    }

    public boolean addWorker(List<Worker> workers, Worker newWorker) {
        String iD = valid.inputString("Enter ID");
        //check if iD exist
        if (valid.checkID(workers, iD) != -1) {
            return false;
        }
        newWorker.setiD(iD);
        newWorker.setName(valid.inputString("Enter Name"));
        newWorker.setAge(valid.inputAge("Enter Age"));
        newWorker.setSalary(valid.inputDouble("Enter salary"));
        newWorker.setWorkLocation(valid.inputString("Enter Work Location"));
        newWorker.setDate(valid.inputDate("Enter date"));
        workers.add(newWorker);
        return true;
    }

    public boolean changeSalary(List<Worker> workers, int location, int type) {
        double change = valid.inputDouble("Enter salary");
        Worker new_worker = new Worker();
        new_worker.setiD(workers.get(location).getiD());
        new_worker.setName(workers.get(location).getName());
        new_worker.setAge(workers.get(location).getAge());
        new_worker.setWorkLocation(workers.get(location).getWorkLocation());
        switch (type) {
            case 1: {
                new_worker.setSalary(workers.get(location).getSalary() + change);
                new_worker.setDate(valid.inputDate("Enter date of change salary"));
                workers.add(new_worker);
                break;
            }
            case 2: {
                if (change > workers.get(location).getSalary()) {
                    return false;
                }
                new_worker.setDate(valid.inputDate("Enter date of change salary"));
                new_worker.setSalary(workers.get(location).getSalary() - change);
                workers.add(new_worker);
                break;
            }
        }
        return true;
    }

    private void setStatus(List<Worker> workers) {
        workers.get(0).setStatus("Neutral");
        for (int i = 1; i < workers.size(); i++) {
            if(workers.get(i).getName().equals(workers.get(i-1).getName())){
                if (workers.get(i).getSalary() < workers.get(i - 1).getSalary()) {
                    workers.get(i).setStatus("Down");
                } else if (workers.get(i).getSalary() > workers.get(i - 1).getSalary()) {
                    workers.get(i).setStatus("Up");
                } else {
                    workers.get(i).setStatus(workers.get(i - 1).getStatus());
                }
            }
            else{
                workers.get(i).setStatus("Neutral");
            }
        }
    }

    public void display(List<Worker> workers) {
        Collections.sort(workers, new Library());
        setStatus(workers);
        System.out.printf("%-15s%-20s%-15s%-15s%-20s%-15s%s\n",
                "Code", "Name", "Age", "Salary",
                "Location", "Status", "Date");
        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}