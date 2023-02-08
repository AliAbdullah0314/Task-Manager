package sample;

import java.time.LocalDate;

public class TaskStructure
{
    String name;
    LocalDate dateset;
    LocalDate datedue;
    LocalDate datecompleted;
    String subject;
    Boolean completed;

    public TaskStructure(LocalDate datedue, String taskname, String subject, LocalDate dateset, LocalDate datecompleted, Boolean completed)
    {
        this.datedue=datedue;
        name=taskname;
        this.subject=subject;
        this.dateset=dateset;
        this.datecompleted=datecompleted;
        this.completed=completed;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateset() {
        return dateset;
    }

    public LocalDate getDatedue() {
        return datedue;
    }

    public LocalDate getDatecompleted() {
        return datecompleted;
    }

    public String getSubject() {
        return subject;
    }

    public Boolean getCompleted() {
        return completed;
    }


}
