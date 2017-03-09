/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictor.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Patricio
 */
public class MoveRoad {
    private String date;
    private String time;
   private  Car car;
   

    public MoveRoad(String date, String time, Car car) {
        this.date = date;
        this.time = time;
        this.car = car;
    }
    public MoveRoad() {
    }

   
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
   

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public boolean horarioPicoyPlaca(String t) throws ParseException
    {
         boolean circular=true;
        Date t0=null;
        Date t1=null;
        Date t2=null;
        Date t3=null;
        Date t4=null;
       SimpleDateFormat df = new SimpleDateFormat("HH:mm");
       t0=df.parse(t);
       t1=df.parse("7:00");
       t2=df.parse("9:30");
       t3=df.parse("16:00");
       t4=df.parse("19:30");
       String como=df.format(t3);
       if((t0.getTime()>=t1.getTime() && t0.getTime()<=t2.getTime())|| (t0.getTime()>=t3.getTime() && t0.getTime()<=t4.getTime()))
       {
           circular=false;
       }
       
       
        
        return circular;
    }
   
    
}
