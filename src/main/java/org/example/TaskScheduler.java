package org.example;

import java.time.LocalDate;
import java.util.Objects;
import java.util.TreeMap;

class Task implements  Comparable<Task> {

    private String title;
    private LocalDate dueDate;

    public Task(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title);
        result = 31 * result + Objects.hashCode(dueDate);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "dueDate=" + dueDate +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int compareTo(Task o) {
        if (this.dueDate == null && o.dueDate == null) return 0;
        if (this.dueDate == null) return -1;
        if (o.dueDate == null) return 1;
        return this.dueDate.compareTo(o.dueDate);
    }
}

    public class TaskScheduler {

        TreeMap<Task, String> scheduler = new TreeMap<>();

        public void scheduleTask(Task task){
            scheduler.put(task, task.getTitle());
        }

        public Task getNextTask(){

            return scheduler.firstKey();

        }

        public static void main(String[] args) {
            TaskScheduler scheduler = new TaskScheduler();

            scheduler.scheduleTask(new Task("Write Report", LocalDate.of(2023, 4, 15)));
            scheduler.scheduleTask(new Task("Pay Bills", LocalDate.of(2023, 4, 10)));

            System.out.println(scheduler.getNextTask());  // Output should be Task(title='Pay Bills', dueDate=2023-04-10)
        }
}
