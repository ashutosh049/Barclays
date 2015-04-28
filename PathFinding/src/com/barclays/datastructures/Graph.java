package com.barclays.datastructures;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	BiDiMap<String, Integer>	labelMap;
	BiDiMap<String, String>	departureMap;
	int[][]			costMat;
	int[][]			paths;
	static int VERY_LARGE_VALUE	=	99999;

	public Graph() {
		labelMap	=	new BiDiMap<String,Integer>();
		departureMap	=	new BiDiMap<String,String>();
	}

	public void formGraph(ArrayList<Edge> edgeList) {

		populateNodeMap(edgeList);
		populateCostMatrix(edgeList);

	}	

	private void populateCostMatrix(ArrayList<Edge> edgeList) 
	{
		costMat	=	new int[labelMap.size()+1][labelMap.size()+1];
		paths	=	new int[labelMap.size()+1][labelMap.size()+1];

		for(int i=0;i<costMat.length;++i)
		{
			Arrays.fill(costMat[i], VERY_LARGE_VALUE);
			Arrays.fill(paths[i], VERY_LARGE_VALUE);
		}


		for(Edge edge:edgeList)
		{
			String label1 = edge.getLabel1();
			String label2 = edge.getLabel2();

			int id1 = labelMap.getValueForKey(label1);
			int id2	= labelMap.getValueForKey(label2);

			costMat[id1-1][id2-1] = edge.getTravel_time();
			costMat[id2-1][id1-1] = edge.getTravel_time();

			paths[id1-1][id2-1]	=	id2-1;
			paths[id2-1][id1-1]	=	id1-1;
		}
	}

	private void populateNodeMap(ArrayList<Edge> edgeList) 
	{
		for(Edge edge:edgeList)
		{
			String label1 = edge.getLabel1();
			String label2 = edge.getLabel2();

			if(!labelMap.containsKey(label1))
			{
				labelMap.put(label1, labelMap.size()+1);
			}

			if(!labelMap.containsKey(label2))
			{
				labelMap.put(label2, labelMap.size()+1);
			}
		}

	}

	public void computeShortestPaths(ArrayList<Edge> edgeList) 
	{
		int numVertices	=	labelMap.size();

		for(int k=0;k<numVertices;++k)
		{
			for(int i=0;i<numVertices;++i)
			{
				for(int j=0;j<numVertices;++j)
				{
					if(i==j)
						continue;

					if(costMat[i][k]+costMat[k][j] < costMat[i][j])
					{
						costMat[i][j] = costMat[i][k]+costMat[k][j];
						paths[i][j]  = paths[i][k];
					}
				}
			}
		}
	}

	public void computeDepartureMap(ArrayList<Departures> departureList) 
	{
		for(Departures departure:departureList)
		{
			String key	=	departure.getFlight_id();
			String value	=	departure.getLabel();

			if(departureMap.containsKey(key))
				continue;

			departureMap.put(key, value);
		}
	}

	public String getPathNWeight(String entry_point_label, String flight_id) 
	{
		int src	= -1;

		if(!labelMap.containsKey(entry_point_label))
		{
			return null;
		}

		src	=	labelMap.getValueForKey(entry_point_label);

		String dest_label	= null;

		if(flight_id.equalsIgnoreCase("ARRIVAL"))
		{
			dest_label	=	"BaggageClaim";
		}
		else
		{

			if(!departureMap.containsKey(flight_id))
			{
				return null;
			}

			dest_label	= departureMap.getValueForKey(flight_id);

			if(!labelMap.containsKey(dest_label))
			{
				return null;
			}
		}
		int dest = labelMap.getValueForKey(dest_label);

		ArrayList<Integer>	vertices	=	getPaths(src-1,dest-1);
		if(vertices==null)
		{
			return null;
		}

		int cost	=	costMat[src-1][dest-1];
		String final_String	=	new String();
		for(Integer v :vertices)
		{
			final_String += labelMap.getKeyForValue(v+1)+" ";
		}

		final_String += ":" + cost;

		return final_String;
	}

	private ArrayList<Integer> getPaths(int src, int dest) {

		if(paths[src][dest] == VERY_LARGE_VALUE)
			return null;

		ArrayList<Integer> nodes_present_in_way	= new ArrayList<Integer>();
		nodes_present_in_way.add(src);

		while(src != dest && paths[src][dest] != VERY_LARGE_VALUE)
		{
			src	=	paths[src][dest];
			nodes_present_in_way.add(src);
		}

		return nodes_present_in_way;
	}



}
