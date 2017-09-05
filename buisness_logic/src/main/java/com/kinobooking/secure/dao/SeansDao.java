package com.kinobooking.secure.dao;

import com.kinobooking.secure.entity.Seans;
import com.kinobooking.secure.entity.Seat;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Екатерина on 24.08.2017.
 */
public interface SeansDao {
    public List<Seans> findByThreeD(int i);

    public List<Seans> findByDate(Date date, int i);

    public List<Seans> findByCinemaThreeD(String cinemaName, int i);

    public List<Seans> findByCinemaDate(String cinemaName, Date date, int i);

    public List<Seans> findByFilmThreeD(String filmName, int i);

    public List<Seans> findByFilmDate(String filmName, Date date, int i);

    public List<Seans> findByFilmCinemaThreeD(String filmName, String cinemaName, int i);

    public List<Seans> findByFilmCinemaDate(String filmName, String cinemaName, Date date, int i);
    public Set<Integer> getHallRowBySeabsId(int seansId);
    public List<Seat> getSeatsBySeansId(int seansId);
}