package com.barclays.datastructures;

public class Edge {

	private String label1;
	private String label2;
	private int travel_time;

	public Edge(String label1,String label2,int time)
	{
		this.label1				=	label1;
		this.label2				=	label2;
		this.travel_time		=	time;
	}


	public String getLabel1() {
		return label1;
	}


	public String getLabel2() {
		return label2;
	}

	public int getTravel_time() {
		return travel_time;
	}

}
