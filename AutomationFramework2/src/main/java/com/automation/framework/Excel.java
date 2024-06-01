package com.automation.framework;

import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	private static String projectPath=System.getProperty("user.dir");
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	
	public Excel()throws Throwable{
		workbook = new XSSFWorkbook(projectPath+"/testdata/data.xlsx");
	}
	
	public Excel(String fileName)throws Throwable{
		workbook = new XSSFWorkbook(projectPath+"/testdata/"+fileName+".xlsx");
	}
	
	public ArrayList<Object> getColHeaders(String sheetName)throws Throwable{
		ArrayList<Object> arr=new ArrayList<Object>();
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		int startidx=row.getFirstCellNum();
		int endidx = row.getLastCellNum();
		for(int i=startidx; i<endidx;i++) {
			arr.add(getCellValue(row.getCell(i)));
		}
		return arr;
	}
	
	public ArrayList<Object> getCellDataByColName(String sheetName,String colName)throws Throwable{
		ArrayList<Object> arr= new ArrayList<Object>();
		int colIndex = getColHeaders(sheetName).indexOf(colName);
		sheet = workbook.getSheet(sheetName);
		int endidx=sheet.getPhysicalNumberOfRows();
		for(int i=1;i<endidx;i++) {
			arr.add(getCellValue(sheet.getRow(i).getCell(colIndex)));
		}
		return arr;
	}
	
	public Object getCellValue(XSSFCell cell) {
		switch(cell.getCellType()) {
		case NUMERIC:
			return cell.getNumericCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case STRING:
			return cell.getStringCellValue();
		case BLANK:
			return cell.getRawValue();
	    default:
	    	return null;
		}
	}
	
}
