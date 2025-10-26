package com.VehicleRentalSystem;

public class Rental {
	private Customer customer;
	private Vehicle vehicle;
	private int days;
	
	public Rental(Customer customer, Vehicle vehicle,int days) {
		this.customer=customer;
		this.vehicle=vehicle;
		this.days=days;
		
	}
	
	public void displayRentalDetails() {
		System.out.println("1.CustomerName: "+customer.getName()+" 2.VehicleBrand: "+vehicle.getbrand()+" 3.NoOfDays: "+days);
		System.out.println("Total RentalCost is :"+vehicle.RentalCost(days));
	}

}
