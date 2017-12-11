package model;

import java.util.ArrayList;
import javafx.beans.property.*;

/**
 * Class that implements flights.
 * @author RoyChiu
 *
 */
public class Plane {
	/**
	 * Class properties.
	 */
	private StringProperty destination;
	private DoubleProperty rate;
	private DoubleProperty distance;
	private DoubleProperty ticketCost;
	
	/**
	 * Create a plane object from inputed data.
	 * @param location destination of flight
	 * @param maxSeats number of seats for flight
	 * @param cost cost of flight
	 * @param time duration of flight
	 */
	public Plane(final String location, final double cost, final double dist) {
		this.rate = new SimpleDoubleProperty(cost);
		this.destination = new SimpleStringProperty(location);
		this.distance = new SimpleDoubleProperty(dist);
		this.ticketCost = new SimpleDoubleProperty(cost * dist);
	}
	
	/**
	 * Get the cost of the flight.
	 * @return cost of flight
	 */
	public DoubleProperty getRate() {
		return rate;
	}
	
	/**
	 * Change the rate of the flight.
	 * @return true if rate changed successfully.
	 */
	public boolean setRate(final double newRate) {
		if (newRate >= 0) {
			this.rate = new SimpleDoubleProperty(newRate);
			setTicketCost();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the cost of one ticket to reach destination.
	 * @return price of ticket
	 */
	public DoubleProperty getTicketCost() {
		return ticketCost;
	}
	
	/**
	 * Set the cost of a ticket for flight based on rate.
	 */
	public void setTicketCost() {
		ticketCost = new SimpleDoubleProperty(rate.getValue() * distance.getValue());
	}
	
	/**
	 * Get list of destinations for flights.
	 * @return arrival location
	 */
	public StringProperty getDestination() {
		return destination;
	}
	
	/**
	 * Get the distance of the flight.
	 * @return distance
	 */
	public DoubleProperty getDistance() {
		return distance;
	}
}
