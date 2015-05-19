package unabhaengige_klassen;

import javax.swing.table.AbstractTableModel;

public class AdaptedTableModel extends AbstractTableModel
{
    private final String[] _columns;
    private String[][] _tableData;
    
    public AdaptedTableModel(String[][] tableData, String[] columns)
    {
        _tableData = tableData;
        _columns = columns;
    }

    @Override
    public int getColumnCount()
    {
        return _columns.length;
    }

    @Override
    public int getRowCount()
    {
        return _tableData.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return _tableData[rowIndex][columnIndex];
    }
    
    public String getColumnName(int column)
    {
        return _columns[column];
    }
}
