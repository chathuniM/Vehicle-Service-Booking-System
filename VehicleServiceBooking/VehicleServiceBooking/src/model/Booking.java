/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Booking {


    private int bookingId;
    private String date;
    private String service;


    public Booking(int bookingId,String date,String service){

        this.bookingId=bookingId;
        this.date=date;
        this.service=service;

    }


    public String getDate(){
        return date;
    }


    public String getService(){
        return service;
    }

}
