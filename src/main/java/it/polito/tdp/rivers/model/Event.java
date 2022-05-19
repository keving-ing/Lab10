package it.polito.tdp.rivers.model;

public class Event implements Comparable<Event>{
	
	int time;
	double flusso;
	double probabilita;
	
	public Event(int time, double flusso, double prob) {
		super();
		this.time = time;
		this.flusso = flusso;
		this.probabilita = prob;
	}

	public int getTime() {
		return time;
	}

	public double getFlusso() {
		return flusso;
	}
	
	@Override
	public int compareTo (Event o) {
		return this.time - o.time;
	}
	
	

}

