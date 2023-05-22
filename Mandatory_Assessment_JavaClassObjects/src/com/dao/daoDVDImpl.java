package com.dao;
import com.model.DVD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class daoDVDImpl implements daoDVD {
    FileOutputStream fileOut;
    ObjectOutputStream objectOut;

    FileInputStream fileIn;
    ObjectInputStream objectIn;

    ArrayList<DVD> list;

    @Override
    public void loadCollection() {
        try {
            this.fileIn = new FileInputStream("storage.txt");
            this.objectIn = new ObjectInputStream(this.fileIn);
            this.list = (ArrayList<DVD>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            list = new ArrayList<>();
        }
    }

    @Override
    public void saveCollection() {
        try {
            this.fileOut = new FileOutputStream("storage.txt");
            this.objectOut = new ObjectOutputStream(this.fileOut);

            objectOut.writeObject(this.list);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDVD(String title, String releaseDate, String mpAARating, String directorName, String studioName, String userRating) {
        list.add(new DVD(title, releaseDate, mpAARating, directorName, studioName, userRating));
    }

    @Override
    public void removeDVD(String title) {
        list.remove(this.getDVD(title));
    }

    @Override
    public void editDVD(int choice, String string, String title) {
        DVD dvd = this.getDVD(title);
        switch (choice) {
            case 1 -> dvd.setTitle(string);
            case 2 -> dvd.setReleaseDate(string);
            case 3 -> dvd.setMpAArating(string);
            case 4 -> dvd.setDirectorName(string);
            case 5 -> dvd.setStudioName(string);
            case 6 -> dvd.setUserRating(string);
        }
    }

    @Override
    public ArrayList<DVD> listAllDVD() {
        return this.list;
    }

    @Override
    public DVD displayDVD(String title) {
        return this.getDVD(title);
    }

    @Override
    public boolean searchForDVD(String title) {
        List<DVD> dvdList = this.list.stream().filter(dvd -> dvd.getTitle().equals(title)).toList();
        return dvdList.size() == 1;
    }

    private DVD getDVD(String title) {
        return this.list.stream().filter(dvd -> dvd.getTitle().equals(title)).findFirst().get();
    }
}
