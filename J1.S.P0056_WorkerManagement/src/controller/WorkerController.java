package controller;

import model.Worker;
import repository.WorkerRepository;
import view.Menu;

/**
 *
 * @author GoldCandy
 */
public class WorkerController extends Menu {

    protected static String[] mc = {"Add Worker", "Up salary", "Down salary",
        "Display Information salary", "Exit"};
    Worker worker;
    WorkerRepository repo;

    public WorkerController() {
        super("======== Worker Management =========", mc);
        worker = new Worker();
        repo = new WorkerRepository();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1: {
                System.out.println("--------- Add Worker ----------");
                repo.addWorker(worker.getWorkers(), new Worker());
                break;
            }
            case 2: {
                System.out.println("------- Up Salary --------");
                repo.changeSalary(worker.getWorkers(), 1);
                break;
            }
            case 3: {
                System.out.println("------- Down Salary --------");
                repo.changeSalary(worker.getWorkers(), 2);
                break;
            }
            case 4: {
                System.out.println("---------------------- Display Information Salary ----------------------");
                repo.display(worker.getWorkers());
                break;
            }
            case 5: {
                System.out.println("EXIT...");
                System.exit(0);
            }
            default: {
                System.out.println("No such choice!");
            }
        }
    }
}