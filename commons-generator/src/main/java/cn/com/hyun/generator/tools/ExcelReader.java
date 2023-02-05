package cn.com.hyun.generator.tools;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
	
	private HSSFWorkbook workbook = null;        
	private HSSFSheet worksheet = null; 
	
	/**         
	 *
	 * @param filePath         
	 * @throws FileNotFoundException         
	 * @throws IOException         
	 **/        
	public ExcelReader(String filePath) throws FileNotFoundException, IOException{                
		
		this.workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(filePath)));        
	
	}                
	
	/**         

	 * @param sheetName         
	 * @return         
	 **/        
	public boolean setSheet(String sheetName){                
		
		this.worksheet = this.workbook.getSheet(sheetName);                
		return this.worksheet != null;        
		
	}                
	
	/**         

	 * @return         
	 * @throws Exception          
	 **/        
	public int getLastRowNum() throws Exception{ 
		
		this.isSetSheet();                
		return this.worksheet.getLastRowNum();
       
	}                
	
	/**         

	 * @return         
	 * @throws Exception         
	 **/        
	public int getFirstRowNum() throws Exception{                
		this.isSetSheet();                
		return this.worksheet.getFirstRowNum();        
	}                
	/**         

	 * @param rownum         
	 * @param colnum         
	 * @return         
	 * @throws Exception         
	 **/        
	public Integer getInt(int rownum, int colnum) throws Exception{                
		this.isSetSheet();                
		HSSFCell cell = this.getCell(rownum, colnum);                
		if(cell != null){                        
			return (int)cell.getNumericCellValue();                
		}                
		return null;        
	}                
	
	/**         

	 * @param rownum         
	 * @param colnum         
	 * @return         
	 * @throws Exception         
	 **/        
	public Double getDouble(int rownum, int colnum) throws Exception{                
		this.isSetSheet();                
		HSSFCell cell = this.getCell(rownum, colnum);                
		if(cell != null){                        
			return cell.getNumericCellValue();                
		}                
		return null;        
	}                
	
	/**         

	 * @param rownum         
	 * @param colnum         
	 * @return         
	 * @throws Exception         
	 **/        
	public Boolean getBoolean(int rownum, int colnum) throws Exception{                
		this.isSetSheet();                
		HSSFCell cell = this.getCell(rownum, colnum);                
		if(cell != null){                        
			return cell.getBooleanCellValue();                
		}                
		return null;        
	}                

	public String getString(int rownum, int colnum) {   
		try {
			this.isSetSheet();                    
		} catch(Exception e) {
			e.printStackTrace();
		}
		HSSFCell cell = this.getCell(rownum, colnum);  
		try {         
			if(cell != null){                        
				return cell.getRichStringCellValue().getString().trim().replace('\r', ' ').replace('\n', ' ');                
			}  
		} catch(Exception e) {
			return String.valueOf(cell.getNumericCellValue()).trim(); 
		}

		return null;        
	}                


	public String getStringNumber(int rownum, int colnum) {   
		try {
			this.isSetSheet();                    
		} catch(Exception e) {
			e.printStackTrace();
		}
		HSSFCell cell = this.getCell(rownum, colnum);  
		try {         
			if(cell != null){                        
				return cell.getRichStringCellValue().getString().trim();                
			}  
		} catch(Exception e) {
			double num = cell.getNumericCellValue();
			int intnum = (int) num;
			return String.valueOf(intnum);
		}

		return null;        
	}
	/**         

	 * @param rownum         
	 * @param colnum         
	 * @return         
	 **/        
	@SuppressWarnings("deprecation")
	protected HSSFCell getCell(int rownum, int colnum){                
		HSSFRow row = this.worksheet.getRow(rownum);                
		if(row != null){                        
			return row.getCell((short)colnum);                
		}                
		return null;        
	}        
	
	/**         

	 * @throws Exception         
	 **/        
	protected void isSetSheet() throws Exception {                
		 if(this.worksheet == null){                        
			 throw new Exception("Please set worksheet by \"setSheet\" method.");                
		 }        
	}
	
}

