package common;

import java.util.Comparator;
import model.Worker;

/**
 *
 * @author GoldCandy
 */
public class Library implements Comparator<Worker> {

    @Override
    public int compare(Worker o1, Worker o2) {
        int iDCompare = o1.getiD().compareTo(o2.getiD());
        if (iDCompare != 0) {
            return iDCompare;
        } else {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

}