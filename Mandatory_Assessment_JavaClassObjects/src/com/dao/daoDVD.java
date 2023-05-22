package com.dao;

import com.model.DVD;

import java.util.ArrayList;

public interface daoDVD {
    void loadCollection();
    void saveCollection();
    void addDVD(String title, String releaseDate, String mpAARating, String directorName, String studioName, String userRating);
    void removeDVD(String title);
    void editDVD(int choice, String string, String title);
    ArrayList<DVD> listAllDVD();
    DVD displayDVD(String title);
    boolean searchForDVD(String title);
}
