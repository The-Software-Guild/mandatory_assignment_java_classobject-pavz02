package com.view;

import com.model.DVD;

import java.util.Scanner;

public class DVDView {
    Scanner scanner;
    public DVDView() {
        this.scanner = new Scanner(System.in);
    }
    public int displayOptions() {
        System.out.println("#####################################################################################");
        System.out.print("""
                Welcome to the DVD application! What would you like to do?
                1: Add a DVD
                2: Remove a DVD
                3: Edit a DVD
                4: List all DVDs
                5: Display an existing DVD
                6: Search for an existing DVD
                7: Quit application
                """);
        System.out.println("#####################################################################################");
        
        return this.getInputInt();
    }

    public int displayEditOptions() {
        System.out.println("#####################################################################################");
        System.out.print("""
                Which field would you like to edit first?
                1: title
                2: release date
                3: mpAA rating
                4: director name
                5: studio name
                6: user rating
                7: end editing
                """);
        System.out.println("#####################################################################################");

        return this.getInputInt();
    }

    public String askForTitle() {
        System.out.println("What is the title of the DVD?");
        return this.getInputString();
    }

    public String askForDateReleased() {
        System.out.println("When was the DVD released?");
        return this.getInputString();
    }

    public String askForMpaaRating() {
        System.out.println("What is the mpAA rating of the DVD?");
        return this.getInputString();
    }

    public String askForDirectorName() {
        System.out.println("Who is the director of the DVD?");
        return this.getInputString();
    }

    public String askForStudioName() {
        System.out.println("Where was the studio for the DVD?");
        return this.getInputString();
    }

    public String askForUserRating() {
        System.out.println("What is the user rating for the DVD?");
        return this.getInputString();
    }

    private int getInputInt() {
        System.out.print("Enter your choice here: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private String getInputString() {
        System.out.print("Enter your choice here: ");
        return scanner.nextLine();
    }

    public void printDVDExists() {
        System.out.println("The DVD already exists");
    }

    public void printDVDDoesNotExist() {
        System.out.println("The DVD does not exist");
    }

    public void printDVD(DVD dvd) {
        System.out.println(dvd);
    }

}
