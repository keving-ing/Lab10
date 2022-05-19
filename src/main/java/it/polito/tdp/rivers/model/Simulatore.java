package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

public class Simulatore {
	
	// Coda degli eventi
	private PriorityQueue<Event> queue;
	
	// Parametri di simulazione
	double q; //capienza totale del bacino
	double fmed; //quantitá media del flusso medio
	List<Double> flows;
	
	// Output della simulazione
	int ngiorni;
	double cmed;
	
	// Stato del mondo simulato
	double c; //quantitá presente giorno per giorno
	
	Model m = new Model();
	int GIORNI_SIMULAZIONE;
	
	
	int cont = 0;
	
	

	public Simulatore(double a, List<Double> flows) {
		
		this.fmed = a;
		this.flows = flows;
	}



	public void init(Double k, River r) {
		
		m.getDateRivers(r);
		
		GIORNI_SIMULAZIONE = m.n_misurazioni;
		
		this.q = fmed*30*k*86400;
		
		this.c = q/2;
		
		double probabilita =  Math.random();
		
		this.queue = new PriorityQueue<Event>();
		
		this.queue.add(new Event(1, flows.get(0),probabilita));
		
	}
	
	public void run() {
		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}



	private void processEvent(Event e) {
		
		double entrata = e.getFlusso();
		double uscita = 0.8 * this.fmed;
		
		
		
		if(e.probabilita>0.05)  //il cliente si ferma al bancone
		{
			uscita = 10 * this.fmed;
		}
		
		if(e.getTime() > this.GIORNI_SIMULAZIONE)
		{
			return;
		}
		
		if((c + entrata) < uscita)  //non riesco a garantire il flusso di uscita minimo
		{
			this.ngiorni ++;
		}
		
		if((c + entrata - uscita) > q)  //non posso superare la capacitá del bacino
		{
			uscita += q - (c + entrata - uscita); 
		}
		
		System.out.println(entrata-uscita);
		this.c += entrata - uscita;
		this.cmed += c;
		
		this.queue.add(new Event (e.getTime()+1, flows.get(cont), e.probabilita));
		cont ++;
		
	}



	public int getNgiorni() {
		return ngiorni;
	}



	public void setNgiorni(int ngiorni) {
		this.ngiorni = ngiorni;
	}



	public double getCmed() {
		return cmed;
	}



	public void setCmed(double cmed) {
		this.cmed = cmed;
	}
	
	
	
	

}
