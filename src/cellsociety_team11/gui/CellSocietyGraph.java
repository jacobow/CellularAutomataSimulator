package cellsociety_team11.gui;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class CellSocietyGraph extends LineChart<Number, Number>{
	private ResourceBundle resourceBundle;
	private Map<Object, XYChart.Series<Number, Number>> seriesMap;
	private int timeElapsed;
	
	public CellSocietyGraph(Axis<Number> simulationTime, Axis<Number> stateCount, ResourceBundle resourceBundle) {
		super(simulationTime, stateCount);
		this.resourceBundle = resourceBundle;
		this.timeElapsed = 0;
		this.seriesMap = new HashMap<Object, XYChart.Series<Number, Number>>();
		initGraph();
	}
	
	public void addData(Map<Object, Integer> dataMap){
		for (Object currKey : dataMap.keySet()){
			XYChart.Data<Number, Number> newData = new XYChart.Data<Number, Number>(timeElapsed, dataMap.get(currKey));
			XYChart.Series<Number, Number> currSeries;
			if (seriesMap.containsKey(currKey)){
				currSeries = seriesMap.get(currKey);
			}
			else{
				currSeries = new XYChart.Series<Number, Number>();
				this.getData().add(currSeries);
			}
			currSeries.getData().add(newData);
			seriesMap.put(currKey, currSeries);
			
		}
		this.timeElapsed++;
	}
	
	private void initGraph(){
		this.setTitle(this.resourceBundle.getString("ChartTitle"));
	}
	

}
