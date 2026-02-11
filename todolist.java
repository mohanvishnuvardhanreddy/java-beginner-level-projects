import java.text.SimpleDateFormat;
import java.util.*;

public class todolist {

    public static void main(String[] args) {
        ArrayList<String> Names = new ArrayList<>();
        ArrayList<Date> taskDate = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the To-Do List Application!");

        while (true) {
            System.out.println("Choose one operation:");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Display Tasks");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(sc.nextLine());
                String value = userChoice(choice, Names, taskDate, sc);

                if ("Exiting application. Goodbye!".equals(value)) {
                    System.out.println(value);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (1-4).\n");
            }
        }
        sc.close();
    }

    public static String userChoice(int choice, ArrayList<String> t, ArrayList<Date> td, Scanner sc) {
        if (choice == 1) {
            System.out.print("Enter task name: ");
            String tName = sc.nextLine();
            System.out.print("Enter deadline (DD-MM-YYYY): ");
            String deadline = sc.nextLine();
            addTask(t, td, tName, deadline);
        } 
        else if (choice == 2) {
            if (t.isEmpty()) {
                System.out.println("No tasks available to delete.\n");
                return null;
            }
            displayTasks(t, td);
            System.out.print("Enter task number to delete: ");
            try {
                int taskNumber = Integer.parseInt(sc.nextLine());
                deleteTask(t, td, taskNumber);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid task number.\n");
            }
        } 
        else if (choice == 3) {
            displayTasks(t, td); // Call the displayTasks method with taskName, taskDeadlines);
        } 
        else if (choice == 4) {
            return "Exiting application. Goodbye!";
        } 
        else {
            System.out.println("Invalid choice!\n");
        }
        return null;
    }

    public static void deleteTask(ArrayList<String> taskNames, ArrayList<Date> taskDeadlines, int taskNumber) {
        int taskIndex = taskNumber - 1;

        // Validation: ensures index is within the current list size
        if (taskIndex >= 0 && taskIndex < taskNames.size()) {
            String removedTask = taskNames.get(taskIndex);
            taskNames.remove(taskIndex);
            taskDeadlines.remove(taskIndex);
            System.out.println("Task '" + removedTask + "' deleted successfully!\n");
        } else {
            System.out.println("Invalid task number! No task found at that position.\n");
        }
    }

    public static void addTask(ArrayList<String> taskNames, ArrayList<Date> taskDeadlines, String taskName, String deadline) {
        Date deadlineDate = validateDate(deadline);
        if (deadlineDate != null) {
            taskNames.add(taskName);
            taskDeadlines.add(deadlineDate);
            System.out.println("Task added successfully!\n");
        }
    }

    public static Date validateDate(String deadline) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Rejects invalid dates like 31-02-2023

        try {
            return dateFormat.parse(deadline);
        } catch (Exception e) {
            System.out.println("Invalid date format! Please enter the deadline in DD-MM-YYYY format.\n");
            return null;
        }
    }

    public static void displayTasks(ArrayList<String> taskNames, ArrayList<Date> taskDeadlines) {
        if (taskNames.isEmpty()) {
            System.out.println("No tasks available.\n");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < taskNames.size(); i++) {
            String formattedDeadline = dateFormat.format(taskDeadlines.get(i));
            System.out.println((i + 1) + ". " + taskNames.get(i) + " - Deadline: " + formattedDeadline);
        }
        System.out.println();
    }
}