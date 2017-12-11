package model;

import java.io.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Train {	
	/**
	 * Class properties.
	 */
	private StringProperty destination;
	private DoubleProperty rate;
	private DoubleProperty distance;
	private DoubleProperty ticketCost;
	
	/**
	 * Class constructor to create train objects.
	 * @param location destination of train
	 * @param cost rate per mile of train
	 * @param dist distance to destination
	 */
	public Train(final String location, final double cost, final double dist) {
		this.rate = new SimpleDoubleProperty(cost);
		this.destination = new SimpleStringProperty(location);
		this.distance = new SimpleDoubleProperty(dist);
		this.ticketCost = new SimpleDoubleProperty(cost * dist);
	}
	
	/**
	 * Get rate per mile.
	 * @return rate per mile
	 */
	public DoubleProperty getRate() {
		return rate;
	}
	
	/**
	 * Set new rate per mile for train. Adjusts cost of ticket.
	 * @param newRate new rate per mile
	 */
	public void setRate(final double newRate) {
		if (newRate >= 0) {
			this.rate = new SimpleDoubleProperty(newRate);
			setTicketCost();
		}
	}
	
	/**
	 * Get cost per ticket for this train.
	 * @return cost per ticket
	 */
	public DoubleProperty getTicketCost() {
		return ticketCost;
	}
	
	/**
	 * Set the ticket cost based on rate and distance.
	 */
	public void setTicketCost() {
		ticketCost = new SimpleDoubleProperty(rate.getValue() * distance.getValue());
	}
	
	/**
	 * Get the destination assigned to train.
	 * @return assigned destination
	 */
	public StringProperty getDestination() {
		return destination;
	}
	
	/**
	 * Get the distance traveled to destination of train.
	 * @return distance
	 */
	public DoubleProperty getDistance() {
		return distance;
	}
}
