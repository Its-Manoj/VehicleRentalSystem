package com.VehicleRentalSystem;

public abstract class Vehicle {
	private String vehicleId;
	private String vehicleName;
	private String brand;
	
	public Vehicle(String vehicleId,String vehicleName, String brand) {
		this.vehicleId=vehicleId;
		this.vehicleName=vehicleName;
		this.brand=brand;
		
	}
	
	public abstract double RentalCost(int days);
	
	
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getbrand() {
		return brand;
	}
	public void setbrand(String brand) {
		this.brand = brand;
	}
	
	public void displayInfo() {
		System.out.println("VehicleName: "+vehicleName+" VehicleId: "+vehicleId+" Brand: "+brand);
		
	}

}
