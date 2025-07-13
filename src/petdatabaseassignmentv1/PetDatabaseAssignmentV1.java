package petdatabaseassignmentv1;
import java.util.ArrayList;
import java.util.Scanner;

// This is the main class that runs the pet database program (Release 1)
public class PetDatabaseAssignmentV1 {
    public static void main(String[] args) {
        System.out.println("Pet Database Program\n");

        Scanner sc = new Scanner(System.in);
        PetDatabase petDB = new PetDatabase(); // Create the database object

        int choice;

        // Main menu loop
        do {
            printMenu(); // Show menu options

            // Get user's choice
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1; // Invalid input
            }

            // Perform action based on user's choice
            switch (choice) {
                case 1:
                    petDB.viewAllPets();
                    break;
                case 2:
                    petDB.addMorePets();
                    break;
                case 3:
                    petDB.searchPetsByName();
                    break;
                case 4:
                    petDB.searchPetsByAge();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }

        } while (choice != 5); // Keep running until user chooses to exit
    }

    // This method shows the menu options to the user
    public static void printMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. View all pets");
        System.out.println("2. Add more pets");
        System.out.println("3. Search pets by name");
        System.out.println("4. Search pets by age");
        System.out.println("5. Exit program");
        System.out.print("Your choice: ");
    }
}

// This class represents a single Pet with a name and age
class Pet {
    private static int count = 0; // Used to give each pet a unique ID
    private int id;
    private String name;
    private int age;

    // Constructor that sets the name and age, and assigns a unique ID
    public Pet(String name, int age) {
        this.id = count++;
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

// This class manages the list of pets and allows user to interact with them
class PetDatabase {
    private ArrayList<Pet> pets = new ArrayList<>();

    // Show all pets in the list
    public void viewAllPets() {
        if (pets.isEmpty()) {
            System.out.println("\nNo pets to display.\n");
            return;
        }

        System.out.println("\n+----------------------+");
        System.out.printf("| %3s | %-10s | %3s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.printf("| %3d | %-10s | %3d |\n", i, pet.getName(), pet.getAge());
        }

        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.\n");
    }

    // Add new pets from user input
    public void addMorePets() {
        Scanner sc = new Scanner(System.in);
        int count = 0;

        System.out.println();

        while (true) {
            System.out.print("add pet (name age): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println("Invalid input. Please use: name age");
                continue;
            }

            String name = parts[0];
            int age;

            try {
                age = Integer.parseInt(parts[1]);
                if (age < 0) {
                    System.out.println("Age cannot be negative.");
                    continue;
                }

                pets.add(new Pet(name, age));
                count++;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a number.");
            }
        }

        System.out.println(count + " pets added.\n");
    }

    // Search pets by name
    public void searchPetsByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String nameToFind = sc.nextLine().trim();

        boolean found = false;

        System.out.println("\n+----------------------+");
        System.out.printf("| %3s | %-10s | %3s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet.getName().equalsIgnoreCase(nameToFind)) {
                System.out.printf("| %3d | %-10s | %3d |\n", i, pet.getName(), pet.getAge());
                found = true;
            }
        }

        System.out.println("+----------------------+");

        if (!found) {
            System.out.println("No pets with name \"" + nameToFind + "\" found.\n");
        }
    }

    // Search pets by age
    public void searchPetsByAge() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter age to search: ");

        int ageToFind;

        try {
            ageToFind = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Please enter a number.");
            return;
        }

        boolean found = false;

        System.out.println("\n+----------------------+");
        System.out.printf("| %3s | %-10s | %3s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet.getAge() == ageToFind) {
                System.out.printf("| %3d | %-10s | %3d |\n", i, pet.getName(), pet.getAge());
                found = true;
            }
        }

        System.out.println("+----------------------+");

        if (!found) {
            System.out.println("No pets with age " + ageToFind + " found.\n");
        }
    }
}
