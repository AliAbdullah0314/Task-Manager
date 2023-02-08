package sample;

import java.time.LocalDate;
import java.util.Comparator;

public class DateDueSorter implements Comparator<TaskStructure>
{
    @Override
    public int compare(TaskStructure o1, TaskStructure o2) {
        return o1.getDatedue().compareTo(o2.getDatedue());
    }
}