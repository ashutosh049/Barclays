package com.barcklays.datastructures;

public class Departures {

	private String	flight_id;
	private String	label;
	private String	dest;
	private String	time;


	public Departures(String id,String label, String dest, String time) {

		this.flight_id	=	id;
		this.label	=	label;
		this.dest	=	dest;
		this.time	=	time;

	}

	public String getFlight_id() {
		return flight_id;
	}

	public String getLabel() {
		return label;
	}

	public String getDest() {
		return dest;
	}

	public String getTime() {
		return time;
	}

}
