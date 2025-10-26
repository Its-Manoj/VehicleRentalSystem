package com.VehicleRentalSystem;

public class Bike extends Vehicle{
	private double rentperday=500;

	public Bike(String vehicleId, String brand, String vehicleName) {
		super(vehicleId,vehicleName, brand);
		
	}

	@Override
	public double RentalCost(int days) {
		
		return rentperday*days ;
	}
	

}
