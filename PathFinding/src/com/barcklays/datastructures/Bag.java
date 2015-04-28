package com.barcklays.datastructures;

public class Bag {

	private String id;
	private String entry_point_label;
	private String flight_id;


	public Bag(String id,String label, String flight_id) {

		this.id	=	id;
		this.entry_point_label	=	label;
		this.flight_id		= flight_id;
	}

	public String getId() {
		return id;
	}
	public String getEntry_point_label() {
		return entry_point_label;
	}
	public String getFlight_id() {
		return flight_id;
	}


}
