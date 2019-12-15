package chenhs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtil {
	public static void main(String[] args) {
		File exlfile = new File("E:\\整改事项明细表.xlsx");
		String exlfileFileName = "整改事项明细表.xlsx";
		importExcelData(exlfile, exlfileFileName);
	}
	
	/**
	 * 导入并读取Excel数据
	 * @param exlfile
	 * @param exlfileFileName
	 */
	public static void importExcelData(File exlFile , String exlfileFileName) {
//		JSONArray json = new JSONArray();
		
		
		
		String msg = "";//错误提示
		String fileType = exlfileFileName.substring(exlfileFileName.lastIndexOf("."),exlfileFileName.length());  
		try(InputStream in = new FileInputStream(exlFile);) {
			Workbook book = null;
			if(".xlsx".equals(fileType)) {
//				book = new XSSFWorkbook(in);
				book = new XSSFWorkbook(new FileInputStream(exlFile));
			}else if(".xls".equals(fileType)){
			//  POIFSFileSystem fs = new POIFSFileSystem(in);
				book = new HSSFWorkbook(in);
			}else {
//				json.add("error");
//				this.getResponse().getWriter().write(json.toString());
				return; 
			}
			
			Sheet sheet = book.getSheetAt(0);
			int count = 6; //从第七行开始
			int rowCount = sheet.getPhysicalNumberOfRows();//行数
//			int rowCount = sheet.getLastRowNum();
			
			for(int i = count;i < rowCount;i++) {
				count++;
				Row row  = sheet.getRow(i);
				if(row == null) {
					continue;
				}
//				SupervisionAppinfo info = new SupervisionAppinfo();
				Cell cell = row.getCell(0);
				String cell11  = cell.getStringCellValue();
				String cell12 = row.getCell(1).getStringCellValue();
			}
			
		}catch (Exception e) {
			
		}
		
		
	}
}
