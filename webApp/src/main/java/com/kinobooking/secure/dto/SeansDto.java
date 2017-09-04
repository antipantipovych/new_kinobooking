package com.kinobooking.secure.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Екатерина on 24.08.2017.
 */
public class SeansDto {

    private String filmName;
    private String cinemaName;
    @NotNull(message="Date should be checked")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date seansDate;
    private String seansStringDate;
    private boolean threeD;
    @Min(value=1, message = "Seans should be choosed")
    private int seansId;

    private List<Integer> seatsForBook;

    public SeansDto() {

    }

    public List<Integer> getSeatsForBook() {
        return seatsForBook;
    }

    public void setSeatsForBook(List<Integer> seatsForBook) {
        this.seatsForBook = seatsForBook;
    }

    @Override
    public String toString() {
        return "SeansDto{" +
                "filmName='" + filmName + '\'' +
                ", cinemaName='" + cinemaName + '\'' +
                ", seansDate=" + seansDate +
                ", seansStringDate='" + seansStringDate + '\'' +
                ", threeD=" + threeD +
                ", seansId=" + seansId +
                '}';
    }

    public boolean isThreeD() {
        return threeD;
    }

    public int getSeansId() {
        return seansId;
    }

    public void setSeansId(int seansId) {
        this.seansId = seansId;
    }

    public String getSeansStringDate() {
        return seansStringDate;
    }

    public void setSeansStringDate(String seansStringDate) {
        this.seansStringDate = seansStringDate;
    }

    public void setThreeD(boolean threeD) {
        this.threeD = threeD;
    }

    public Date getSeansDate() {
        return seansDate;
    }

    public void setSeansDate(Date seansDate) {
        this.seansDate = seansDate;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
}
