package com.barcklays.utils;

import com.barcklays.datastructures.Bag;
import com.barcklays.datastructures.Departures;
import com.barcklays.datastructures.Edge;

public class StringUtils {

	public Edge parseConveyor(String line) {

		if(line == null || line.isEmpty())
			return null;

		String[] tokens	=	line.split(" ");
		if(tokens.length < 3)
			return null;

		return new Edge(tokens[0],tokens[1],Integer.parseInt(tokens[2]));

	}

	public Departures parseDeparture(String line) {

		if(line == null || line.isEmpty())
			return null;

		String[] tokens	=	line.split(" ");
		if(tokens.length < 4)
			return null;

		return	new Departures(tokens[0], tokens[1],tokens[2], tokens[3]);
	}

	public Bag parseBags(String line) {
		
		if(line == null || line.isEmpty())
			return null;

		String[] tokens	=	line.split(" ");
		if(tokens.length < 3)
			return null;

		return	new Bag(tokens[0], tokens[1],tokens[2]);
	}


}
