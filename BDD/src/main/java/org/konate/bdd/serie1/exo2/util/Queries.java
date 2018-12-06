package org.konate.bdd.serie1.exo2.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Queries {
	public static ResultSet execute(Statement statement, String sql) throws SQLException {
		return statement.executeQuery(sql);
	}

	public static void printQueryAnswer(ResultSet resultSet, String columnLabel) throws SQLException {
		int i = 0; 
		List<String> result = new ArrayList<>();
		while(resultSet.next()) {
			i++;
			result.add(resultSet.getString(columnLabel));
		}
		if(i==1)
			System.out.println(columnLabel + " -> " + result.get(0));
		else
			System.out.println(columnLabel + " -> " + result);		
	}
	
	public static void prettyPrintQueryAnswer(ResultSet resultSet, List<String> columnLabels) throws SQLException {
		int i = 0; 
		List<String> result = new ArrayList<>();
		for (int j = 0; j < columnLabels.size(); j++) {
			while(resultSet.next()) {
				i++;
				result.add(resultSet.getString(columnLabels.get(j)));
			}
			if(i==1)
				System.out.println(columnLabels.get(j) + " -> " + result.get(0));
			else
				System.out.println(columnLabels.get(j) + " -> " + result);	
		}
	}
	
	/*public static void prettyPrintQueryAnswer(ResultSet resultSet) throws SQLException {
		
		List<List<String>> columns = new ArrayList<>();
		List<String> columnsLabels = new ArrayList<>();
		List<String> columnsValues = new ArrayList<>();
		
		ResultSetMetaData data = resultSet.getMetaData();
		for (int j = 1; j <= data.getColumnCount(); j++) {
			columnsLabels.add(data.getColumnName(j));
		}
		
		for (int i = 0; i < data.getColumnCount(); i++) {
			while(resultSet.next()) {
				columnsValues.add(resultSet.getString(columnsLabels.get(i)));
			}
			columnsValues.add(i, columnsLabels.get(i));
			columns.add(columnsValues);
		}
		
		for (List<String> column : columns) {
			System.out.println(column.remove(0) + " : " + column);
		}
	}*/
}
