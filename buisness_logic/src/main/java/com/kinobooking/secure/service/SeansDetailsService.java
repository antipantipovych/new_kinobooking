package com.kinobooking.secure.service;

import com.kinobooking.secure.dao.CinemaDao;
import com.kinobooking.secure.dao.FilmDao;
import com.kinobooking.secure.dao.SeansDao;
import com.kinobooking.secure.dao.TicketDao;
import com.kinobooking.secure.entity.Seans;
import com.kinobooking.secure.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Екатерина on 24.08.2017.
 */
@Service
public class SeansDetailsService {
    @Autowired
    private FilmDao filmDao;
    @Autowired
    private CinemaDao cinemaDao;
    @Autowired
    private SeansDao seansDao;
    @Autowired
    private TicketDao ticketDao;

    public List<Seat> getSeats(int seansId){return seansDao.getSeatsBySeansId(seansId);}
    public Set<Integer> getHallRows(int seansId){return seansDao.getHallRowBySeabsId(seansId);}
    public List<String> loadFilmNames(){
        return filmDao.getFilmNames();
    }
    public Set<Integer> getBlockedSeats(int seansId){
        return ticketDao.getSeatsBySeansId(seansId);
    }

    public List<String> loadCinemaNames(){
        return cinemaDao.getCinemaNames();
    }

    public List<Seans> findSeanses(String filmName, String cinemaName, Date date, boolean threeD){
        List<Seans> list= null;
        int three=0;
        if(threeD) three=1;
        if(filmName.equals("")){
            if(cinemaName.equals("")){
                if(date==null){
                    list=seansDao.findByThreeD(three);
                }
                else {
                    list = seansDao.findByDate(date, three);
                }
            }
            else{
                if(date==null){
                    list=seansDao.findByCinemaThreeD(cinemaName,three);
                }
                else {
                    list = seansDao.findByCinemaDate(cinemaName,date, three);
                }
            }
        }
        else{
            if(cinemaName.equals("")){
                if(date==null){
                    list=seansDao.findByFilmThreeD(filmName,three);
                }
                else {
                    list = seansDao.findByFilmDate(filmName,date, three);
                }
            }
            else{
                if(date==null){
                    list=seansDao.findByFilmCinemaThreeD(filmName,cinemaName,three);
                }
                else {
                    list = seansDao.findByFilmCinemaDate(filmName,cinemaName,date, three);
                }
            }
        }
        return list;

    }
}
