package com.barclays.drivers;

import java.io.IOException;
import java.util.ArrayList;

import com.barclays.datastructures.Bag;
import com.barclays.datastructures.Departures;
import com.barclays.datastructures.Edge;
import com.barclays.datastructures.Graph;
import com.barclays.utils.FileUtils;
import com.barclays.utils.StringUtils;

public class Main {

	private static final String conveyor_system = "Conveyor System";
	private static final String departure_list = "Departures";
	private static final String bags_list = "Bags";

	public static void main(String[] args) throws IOException {

		//		System.out.println("Enter file name :: ");
		//		FileUtils	fu	= new FileUtils(System.in);
		FileUtils	fu	= new FileUtils("inp");
		StringUtils su	= new StringUtils();
		boolean	conveyor = false;
		boolean	departure = false;
		boolean	bags = false;

		ArrayList<Edge>	edgeList	= new ArrayList<Edge>();
		ArrayList<Departures> departureList	=	new ArrayList<Departures>();
		ArrayList<Bag>		bagsList	= new ArrayList<Bag>();

		Edge	n;
		Departures	d;
		Bag		b;


		String line;
		while ((line = fu.readLine()) != null) 
		{

			if(line.isEmpty())
				continue;


			if(line.contains(conveyor_system))
			{
				conveyor	=	true;
				departure	=	false;
				bags		=	false;
				continue;
			}

			if(line.contains(departure_list))
			{
				conveyor	=	false;
				departure	=	true;
				bags		=	false;
				continue;
			}

			if(line.contains(bags_list))
			{
				conveyor	=	false;
				departure	=	false;
				bags		=	true;
				continue;
			}



			if(conveyor)
			{
				n = su.parseConveyor(line);
				if(n==null)
					continue;
				edgeList.add(n);
			}
			else if(departure)
			{
				d = su.parseDeparture(line);
				if(d==null)
					continue;
				departureList.add(d);
			}
			else if(bags)
			{
				b = su.parseBags(line);
				if(b == null)
					continue;
				bagsList.add(b);
			}
		}

		Graph	g = new Graph();
		g.formGraph(edgeList);
		g.computeShortestPaths(edgeList);


		if(/*bagsList != null && */bagsList.size() <= 0 || departureList.size() <= 0)
		{
			return;
		}

		g.computeDepartureMap(departureList);
		for(Bag bag: bagsList)
		{
			String pathNWeight	=	g.getPathNWeight(bag.getEntry_point_label(),bag.getFlight_id());
			System.out.println(bag.getId()+" "+pathNWeight);
		}

	}
}
