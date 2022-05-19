package it.polito.tdp.rivers.model;

import java.time.LocalDate;

import java.util.List;
import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao;
	List<Double> flow;
	double AvgFlow;
	int n_misurazioni;
	double cmed;
	
	public Model()
	{
		dao = new RiversDAO();
	}
	
	public List<River> getAllRivers()
	{
		return dao.getAllRivers();
	}
	
	public List<LocalDate> getDateRivers(River r)
	{
		List<LocalDate> date = dao.getDateRivers(r);
		this.n_misurazioni = date.size();
		return date;
	}
	
	public double getAvgFlow(River r)
	{
		AvgFlow = dao.getAvgFlow(r);
		return AvgFlow;
	}
	
	public List<Double> getFlows(River r)
	{
		this.flow = dao.getFlows(r);
		return flow;
	}
	
	
	
	public int simula(double k, River r) {
		Simulatore sim = new Simulatore(this.AvgFlow, this.flow) ;
		sim.init(k, r);
		sim.run();
		this.cmed = sim.getCmed();
		return sim.getNgiorni();
	}

	public double getCmed() {
		return cmed;
	}
	
	

}
