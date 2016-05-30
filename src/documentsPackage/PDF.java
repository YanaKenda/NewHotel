package documentsPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF {
	
	private static final Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, new CMYKColor(255, 255, 255, 0));
	private static final Font valueFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 0));
	private static final Font nameFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD, new CMYKColor(255, 255, 255, 0));
	
	private static float[] addElement(float[] a, float e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}
	
	private static void setCellOutlook(PdfPCell cell) {
		cell.setPaddingLeft(10);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	}
	
	public static void createSimplePDF(String tableName, ArrayList<String>titles, ArrayList<String>values)  {
		Document document = new Document();
    	float [] columnWidths = {};
		try
	    {
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Yana/Documents/" + tableName + "-simple.pdf"));      
	        document.open();
	        Paragraph name = new Paragraph(tableName, nameFont);
	        name.setAlignment(Element.ALIGN_CENTER);
	        PdfPTable table = new PdfPTable(titles.size());
	        table.setWidthPercentage(100);
	        table.setSpacingBefore(10f); 
	        table.setSpacingAfter(10f); 
	        for(int i = 0; i < titles.size(); i++) {
	        	columnWidths = addElement(columnWidths, 1f);
	        }
	        table.setWidths(columnWidths);
	        for(int i = 0; i < titles.size(); i++) {
	            PdfPCell titleCell = new PdfPCell(new Phrase(titles.get(i), titleFont));
	            titleCell.setBorderColor(BaseColor.BLUE);
	            setCellOutlook(titleCell);
		        table.addCell(titleCell);
	        }
	        for(int i = 0; i < values.size(); i++) {
	        	PdfPCell valueCell = new PdfPCell(new Phrase(values.get(i), valueFont));
		        valueCell.setBorderColor(BaseColor.BLUE);
		        setCellOutlook(valueCell);
			    table.addCell(valueCell);
	        }
	        document.add(name);
	        document.add(table);
	        document.close();
	        writer.close();
	    } catch (DocumentException e) {
	    	throw new RuntimeException(e);
	    } catch (FileNotFoundException e) {
	    	throw new RuntimeException(e);
		}
	}
	
	public static void createWatermarkedPDF(String tableName, ArrayList<String>titles, ArrayList<String>values) {
		Document document = new Document();
    	float [] columnWidths = {};
		try{
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Yana/Documents/" + tableName + "-watermarked.pdf"));
	         document.open();
	         writer.setPageEvent(new WatermarkListener());
	         Paragraph name = new Paragraph(tableName, nameFont);
		        name.setAlignment(Element.ALIGN_CENTER);
		        PdfPTable table = new PdfPTable(titles.size()); 
		        table.setWidthPercentage(100); 
		        table.setSpacingBefore(10f); 
		        table.setSpacingAfter(10f);
		        for(int i = 0; i < titles.size(); i++) {
		        	columnWidths = addElement(columnWidths, 1f);
		        }
		        table.setWidths(columnWidths);
		        for(int i = 0; i < titles.size(); i++) {
		            PdfPCell titleCell = new PdfPCell(new Phrase(titles.get(i), titleFont));
		            titleCell.setBorderColor(BaseColor.BLUE);
		            setCellOutlook(titleCell);
			        table.addCell(titleCell);
		        }
		        for(int i = 0; i < values.size(); i++) {
		        	PdfPCell valueCell = new PdfPCell(new Phrase(values.get(i), valueFont));
			        valueCell.setBorderColor(BaseColor.BLUE);
			        setCellOutlook(valueCell);
				    table.addCell(valueCell);
		        }
		        document.add(name);
		        document.add(table);
		        document.close();
		        writer.close();
	     } catch (FileNotFoundException e){
	    	 throw new RuntimeException(e);
	     } catch (DocumentException e) {
	    	 throw new RuntimeException(e);
	     }
	}
	
	public static void createProtectedPDF(String tableName, ArrayList<String>titles, ArrayList<String>values) {
		Document document = new Document();
    	float [] columnWidths = {}; 
		try {
			 OutputStream file = new FileOutputStream(new File("C:/Users/Yana/Documents/" + tableName + "-protected.pdf"));
			 PdfWriter writer = PdfWriter.getInstance(document, file);
			 writer.setEncryption("".getBytes(), "".getBytes(),
		                PdfWriter.ALLOW_PRINTING , 
		                PdfWriter.ENCRYPTION_AES_128);
			 document.open();
			 Paragraph name = new Paragraph(tableName, nameFont);
		     name.setAlignment(Element.ALIGN_CENTER);
		     PdfPTable table = new PdfPTable(titles.size());
		     table.setWidthPercentage(100); 
		     table.setSpacingBefore(10f);
		     table.setSpacingAfter(10f); 
		     for(int i = 0; i < titles.size(); i++) {
		    	 columnWidths = addElement(columnWidths, 1f);
		     }
		     table.setWidths(columnWidths);
		     for(int i = 0; i < titles.size(); i++) {
		    	 PdfPCell titleCell = new PdfPCell(new Phrase(titles.get(i), titleFont));
		    	 titleCell.setBorderColor(BaseColor.BLUE);
		    	 setCellOutlook(titleCell);
		    	 table.addCell(titleCell);
		     }
		     for(int i = 0; i < values.size(); i++) {
		    	 PdfPCell valueCell = new PdfPCell(new Phrase(values.get(i), valueFont));
		    	 valueCell.setBorderColor(BaseColor.BLUE);
		    	 setCellOutlook(valueCell);
		    	 table.addCell(valueCell);
		        }   
		     document.add(name);
		     document.add(table);
			 document.close();
			 writer.close();
			 file.close();
		 } catch (IOException e) {
			 throw new RuntimeException(e);
		 } catch (DocumentException e) {
			 throw new RuntimeException(e);
		 }

	}


}
