package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao;
	
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
		return dao.getDateRivers(r);
	}
	
	public double getAvgFlow(River r)
	{
		return dao.getAvgFlow(r);
	}

}
