/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Vehicle {

    private String vehicleNo;
    private String model;
    private String type;


    public Vehicle(String vehicleNo,String model,String type){

        this.vehicleNo=vehicleNo;
        this.model=model;
        this.type=type;

    }


    public String getVehicleNo(){
        return vehicleNo;
    }


    public String getModel(){
        return model;
    }


    public String getType(){
        return type;
    }

}
