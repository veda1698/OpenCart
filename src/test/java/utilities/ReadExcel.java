package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	
	public ReadExcel(String path){
		this.path=path;
	}
	
	
	public int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(path);
		wb= new XSSFWorkbook(fis);
		ws=wb.getSheet(sheetName);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fis = new FileInputStream(path);
		wb= new XSSFWorkbook(fis);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rowNum);
		int cellCount= row.getLastCellNum();
		wb.close();
		fis.close();
		return cellCount;
		
	}
	
	public String getCellData(String sheetName,int rowNum, int cellNum) throws IOException {
		fis= new FileInputStream(path);
		wb= new XSSFWorkbook(fis);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rowNum);
		
		if (row == null) {
	        wb.close();
	        fis.close();
	        return "";
	    }
	    
		cell=row.getCell(cellNum);
	    if (cell == null) {
	        wb.close();
	        fis.close();
	        return "";
	    }
		
		
		String data;
		DataFormatter formatter = new DataFormatter();
		try {
			data= formatter.formatCellValue(cell);
		}catch(Exception e) {
			data= " ";
		}
		
		wb.close();
		fis.close();
		return data;
	}
	
	
	public void setCellData(String sheetName,int rowNum, int cellNum, String data) throws IOException {
		
		//check if sheet files exists if not create one
		File file= new File(path);
		if(!file.exists()) {
			wb= new XSSFWorkbook();
			fos= new FileOutputStream(path);
			wb.write(fos);
		}
		
		
		
		fis= new FileInputStream(path);
		wb= new XSSFWorkbook(fis);
		
		if(wb.getSheetIndex(sheetName)==-1) {
			wb.createSheet(sheetName);
		}
		ws= wb.getSheet(sheetName);
		
		if(ws.getRow(rowNum)==null) {
			ws.createRow(rowNum);
		}
		row=ws.getRow(rowNum);
		cell=row.createCell(cellNum);
		cell.setCellValue(data);
		fos=new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
		
	}
	
	public  void fillGreenColor(String sheetName,int rowNum,int cellNum) throws IOException {
		fis= new FileInputStream(path);
		wb= new XSSFWorkbook(fis);
		ws=wb.getSheet(sheetName);
		row= ws.getRow(rowNum);
		cell= row.getCell(cellNum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos=new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}
	
	public  void fillRedColor(String sheetName,int rowNum,int cellNum) throws IOException {
		fis= new FileInputStream(path);
		wb= new XSSFWorkbook(fis);
		ws=wb.getSheet(sheetName);
		row= ws.getRow(rowNum);
		cell= row.getCell(cellNum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos=new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}
	
	
	
	

}

