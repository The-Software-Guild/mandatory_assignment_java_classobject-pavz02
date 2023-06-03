package com.model;

import java.io.Serializable;
import java.time.LocalDate;

public class DVD implements Serializable {
	String title;
	LocalDate releaseDate;
	String mpAArating;
	String directorName;
	String studioName;
	String userRating;

	public DVD(String title, String releaseDate, String mpAArating, String directorName, String studioName, String userRating) {
		this.title = title;
		this.releaseDate = LocalDate.parse(releaseDate);
		this.mpAArating = mpAArating;
		this.directorName = directorName;
		this.studioName = studioName;
		this.userRating = userRating;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(String date) {
		this.releaseDate = LocalDate.parse(date);
	}

	public void setMpAArating(String mpAArating) {
		this.mpAArating = mpAArating;
	}

	public String getMpAArating() {
		return this.mpAArating;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getDirectorName() {
		return this.directorName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}

	public String getStudioName() {
		return this.studioName;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}

	public String getUserRating() {
		return this.userRating;
	}

	@Override
	public String toString() {
		return "Title: " + this.getTitle() + "\nRelease date: " + this.getReleaseDate() +
				"\nmpAA rating: " + this.getMpAArating() + "\nDirector name: " + this.getDirectorName() +
				"\nStudio name: " + this.getStudioName() + "\nUser rating: " + this.getUserRating() + "\n";
	}
	
}
