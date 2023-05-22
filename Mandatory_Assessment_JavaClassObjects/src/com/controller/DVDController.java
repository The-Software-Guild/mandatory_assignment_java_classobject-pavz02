package com.controller;

import com.dao.daoDVDImpl;
import com.model.DVD;
import com.view.DVDView;

public class DVDController {
    private final DVDView view;
    private final daoDVDImpl dao;
    public DVDController(DVDView view, daoDVDImpl dao) {
        this.view = view;
        this.dao = dao;
    }

    public void start() {
        dao.loadCollection();
        int choice;

        while (true) {
            choice = view.displayOptions();

            if (choice == 7) break;

            if (choice == 4) {
                this.listAllDVD();
                continue;
            }

            String title = view.askForTitle();
            if (!dao.searchForDVD(title)) {
                if (choice == 1) view.printDVDExists();
                else view.printDVDDoesNotExist();
                continue;
            }

            switch (choice) {
                case 1 -> this.addDVD(title);
                case 2 -> this.removeDVD(title);
                case 3 -> this.editDVD(title);
                case 5 -> this.displayDVD(title);
                case 6 -> this.searchForDVD();
            }
        }

        dao.saveCollection();
    }

    private void addDVD(String title) {
        dao.addDVD(title, view.askForDateReleased(), view.askForMpaaRating(),
                view.askForDirectorName(), view.askForStudioName(), view.askForUserRating());
    }

    private void removeDVD(String title) {
        dao.removeDVD(title);
    }

    private void editDVD(String title) {
        int choice = view.displayEditOptions();

        while (choice != 7) {
            switch (choice) {
                case 1 -> {
                    String newTitle = view.askForTitle();
                    dao.editDVD(1, newTitle, title);
                    title = newTitle;
                }
                case 2 -> dao.editDVD(2, view.askForDateReleased(), title);
                case 3 -> dao.editDVD(3, view.askForMpaaRating(), title);
                case 4 -> dao.editDVD(4, view.askForDirectorName(), title);
                case 5 -> dao.editDVD(5, view.askForStudioName(), title);
                case 6 -> dao.editDVD(6, view.askForUserRating(), title);
            }
            choice = view.displayEditOptions();
        }
    }

    private void listAllDVD() {
        for (DVD dvd : dao.listAllDVD()) view.printDVD(dvd);
    }

    private void displayDVD(String title) {
        view.printDVD(dao.displayDVD(title));
    }

    private void searchForDVD() {
        view.printDVDExists();
    }


}
