package documentsPackage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class XLSX {
	
	private static HSSFFont titlesFont(HSSFWorkbook wb) {
	    HSSFFont font= wb.createFont();
	    font.setFontHeightInPoints((short) 14);
	    font.setFontName("Times New Roman");
	    font.setColor(IndexedColors.BLACK.getIndex());
	    font.setBoldweight((short)10);
	    font.setUnderline((byte)5);
	    font.setItalic(false);
		return font;		
	}
	
	private static HSSFFont valuesFont(HSSFWorkbook wb) {
	    HSSFFont font= wb.createFont();
	    font.setFontHeightInPoints((short) 10);
	    font.setFontName("Times New Roman");
	    font.setColor(IndexedColors.BLACK.getIndex());
	    font.setBoldweight((short)5);
	    font.setItalic(false);
		return font;	
	}
	
	private static CellStyle customTitleStyle(HSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THICK);
		style.setBorderLeft(CellStyle.BORDER_THICK);
		style.setBorderRight(CellStyle.BORDER_THICK);
		style.setBorderTop(CellStyle.BORDER_THICK);
        style.setWrapText(true); 
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.ALIGN_FILL);
        style.setFont(titlesFont(wb));
        return style;
	}
	
	private static CellStyle customValueStyle(HSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
        style.setWrapText(true); 
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.ALIGN_FILL);
        style.setFont(valuesFont(wb));
        return style;
	}
	
	
	public static void createXSLX(String name, ArrayList<String>titles, ArrayList<String>values) {
		int rowsCount = 1;
		String excelFileName = "C:\\Users\\Yana\\Documents\\XLSX" + name + ".xls";
		try {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(name) ;

		HSSFRow titlesRow = sheet.createRow(0);
		for(int i = 0; i < titles.size(); i++) {
			HSSFCell cell = titlesRow.createCell(i);
			sheet.setColumnWidth(i, 5000);
			cell.setCellStyle(customTitleStyle(wb)); 
			cell.setCellValue(titles.get(i));
		}
		
		for(int i = 0; i < values.size(); i+= titles.size()) {
			HSSFRow valuesRow = sheet.createRow(rowsCount);
			for(int j = 0; j < titles.size(); j++) {
				HSSFCell cell = valuesRow.createCell(j);
				CellStyle style = wb.createCellStyle();
		        style.setWrapText(true); 
		        cell.setCellStyle(customValueStyle(wb)); 
				cell.setCellValue(values.get(i + j));
			}
			rowsCount ++;
		}
				
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
