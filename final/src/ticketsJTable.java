
/*
 * Final Project - Trouble Ticket System
 * ITMD 411
 * Programmed by Guzel Nasybullina
 * 12.09.2017
 * 
 */
//import needed libraries
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ticketsJTable {
	@SuppressWarnings("unused")
	private final DefaultTableModel mTable = new DefaultTableModel();

	public static DefaultTableModel buildTableModel(ResultSet res) throws SQLException {
		ResultSetMetaData mData = res.getMetaData();
		Vector<String> colNames = new Vector<String>();
		int numberCol = mData.getColumnCount();
		for (int col = 1; col <= numberCol; col++) {
			colNames.add(mData.getColumnName(col));
		}
		// data from the table
		Vector<Vector<Object>> d = new Vector<Vector<Object>>();
		while (res.next()) {
			Vector<Object> v = new Vector<Object>();
			for (int indexCol = 1; indexCol <= numberCol; indexCol++) {
				v.add(res.getObject(indexCol));
			}
			d.add(v);
		}
		// return data and column names
		return new DefaultTableModel(d, colNames);
	}
}