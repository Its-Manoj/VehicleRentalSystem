package com.VehicleRentalSystem;

public class Car extends Vehicle{
	private double rentperday=1000;

	public Car(String vehicleId, String brand, String vehicleName) {
		super(vehicleId, vehicleName, brand);
	
	}

	@Override
	public double RentalCost(int days) {
		return rentperday*days;
		
		
	}
	
	

}
