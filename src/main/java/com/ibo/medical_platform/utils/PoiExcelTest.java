package com.ibo.medical_platform.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class PoiExcelTest {

	public static void main(String[] args) throws IOException, InvalidFormatException {
//		readExcelUtils("D:\\aaa.xlsx");
		Map map  = new HashMap();
		map.put("标题1","内容1");
		map.put("标题2","内容2");
		map.put("标题3","内容3");
		map.put("标题4","内容4");
		map.put("标题5","内容5");
        writeExcel("D:/ddd.xlsx","",Arrays.asList("标题1","标题2","标题3","标题4","标题5"),Arrays.asList(map));
        String s = readExcel("D:/ddd.xlsx", 0);
        System.out.println(s);
    }


	public static void readExcelUtils(String filePath){
		try {
			//速去数据流
			System.out.println("1.读取student.xls工作流");
			//使用到的Excel文件路径   D:\\eclipse\\eclipse-workspace\\poi_demo\\student.xls
			InputStream inp = new FileInputStream(new File(filePath));
			//解析工作簿
			System.out.println("2.解析生成工作簿");
			Workbook workbook = null;
			if(filePath.endsWith("xlsx")) {
				workbook  = new XSSFWorkbook(inp);
			}
			else {
				workbook = new HSSFWorkbook(inp);
			}
			//解析工作表
			int size = workbook.getNumberOfSheets();
			System.out.println("一共有"+size+"个工作表Sheet");
			//循环处理读取每一个工作表中的数据
			for (int i = 0; i <size; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println("读取当前工作表名称"+sheet.getSheetName());
				//得到有效行数
				int rowNumber = sheet.getPhysicalNumberOfRows();
				System.out.println("有效行数"+rowNumber);
				for (int rowIndex = 0; rowIndex < rowNumber; rowIndex++) {
					System.out.println("读取第"+(rowIndex+1)+"行的数据");
					if (rowIndex == 0) {
						continue;
					}
					Row row = sheet.getRow(rowIndex);
					for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
						Cell cell = row.getCell(cellIndex);
						Object cellValue = null;
						if(cell == null){
							continue;
						}
						switch (cell.getCellType()) {
							case NUMERIC:{
								cellValue = String.valueOf(cell.getNumericCellValue());
								break;
							}
							case STRING:{
								cellValue = cell.getStringCellValue();
								break;
							}
							case BOOLEAN:{
								break;
							}
							case FORMULA:{
								break;
							}

							default:
								break;
						}
						System.out.println("列值"+cellValue);
					}

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



    public static final String OFFICE_EXCEL_XLS = "xls";
    public static final String OFFICE_EXCEL_XLSX = "xlsx";

    /**
     * 读取指定Sheet也的内容
     * @param filepath filepath 文件全路径
     * @param sheetNo sheet序号,从0开始,如果读取全文sheetNo设置null
     */
    public static String readExcel(String filepath, Integer sheetNo)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        StringBuilder sb = new StringBuilder();
        Workbook workbook = getWorkbook(filepath);
        if (workbook != null) {
            if (sheetNo == null) {
                int numberOfSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numberOfSheets; i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    if (sheet == null) {
                        continue;
                    }
                    sb.append(readExcelSheet(sheet));
                }
            } else {
                Sheet sheet = workbook.getSheetAt(sheetNo);
                if (sheet != null) {
                    sb.append(readExcelSheet(sheet));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据文件路径获取Workbook对象
     * @param filepath 文件全路径
     */
    public static Workbook getWorkbook(String filepath)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        InputStream is = null;
        Workbook wb = null;
        if (Strings.isBlank(filepath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        } else {
            String suffiex = getSuffiex(filepath);
            if (Strings.isBlank(suffiex)) {
                throw new IllegalArgumentException("文件后缀不能为空");
            }
            if (OFFICE_EXCEL_XLS.equals(suffiex) || OFFICE_EXCEL_XLSX.equals(suffiex)) {
                try {
                    is = new FileInputStream(filepath);
                    wb = WorkbookFactory.create(is);
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (wb != null) {
                        wb.close();
                    }
                }
            } else {
                throw new IllegalArgumentException("该文件非Excel文件");
            }
        }
        return wb;
    }

    private static String readExcelSheet(Sheet sheet) {
        StringBuilder sb = new StringBuilder();

        if(sheet != null){
            int rowNos = sheet.getLastRowNum();// 得到excel的总记录条数
            for (int i = 0; i <= rowNos; i++) {// 遍历行
                Row row = sheet.getRow(i);
                if(row != null){
                    int columNos = row.getLastCellNum();// 表头总共的列数
                    for (int j = 0; j < columNos; j++) {
                        Cell cell = row.getCell(j);
                        if(cell != null){
                            cell.setCellType(CellType.STRING);
                            sb.append(cell.getStringCellValue() + " ");
                            // System.out.print(cell.getStringCellValue() + " ");
                        }
                    }
                    // System.out.println();
                }
            }
        }

        return sb.toString();
    }

    /**
     * 读取指定Sheet页的表头
     * @param filepath filepath 文件全路径
     * @param sheetNo sheet序号,从0开始,必填
     */
    public static Row readTitle(String filepath, int sheetNo)
            throws IOException, EncryptedDocumentException, InvalidFormatException {
        Row returnRow = null;
        Workbook workbook = getWorkbook(filepath);
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(sheetNo);
            returnRow = readTitle(sheet);
        }
        return returnRow;
    }

    /**
     * 读取指定Sheet页的表头
     */
    public static Row readTitle(Sheet sheet) throws IOException {
        Row returnRow = null;
        int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
        for (int i = 0; i < totalRow; i++) {// 遍历行
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            returnRow = sheet.getRow(0);
            break;
        }
        return returnRow;
    }

	/**
	 * 创建Excel文件
	 * @param filepath filepath 文件全路径
	 * @param sheetName 新Sheet页的名字
	 * @param titles 表头
	 * @param values 每行的单元格
	 */
	public static boolean writeExcel(String filepath, String sheetName, List<String> titles,
									 List<Map<String, Object>> values) throws IOException {
		boolean success = false;
		OutputStream outputStream = null;
		if (Strings.isBlank(filepath)) {
			throw new IllegalArgumentException("文件路径不能为空");
		} else {
			String suffiex = getSuffiex(filepath);
			if (Strings.isBlank(suffiex)) {
				throw new IllegalArgumentException("文件后缀不能为空");
			}
			Workbook workbook;
			if ("xls".equals(suffiex.toLowerCase())) {
				workbook = new HSSFWorkbook();
			} else {
				workbook = new XSSFWorkbook();
			}
			// 生成一个表格
			Sheet sheet;
			if (Strings.isBlank(sheetName)) {
				// name 为空则使用默认值
				sheet = workbook.createSheet();
			} else {
				sheet = workbook.createSheet(sheetName);
			}
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth((short) 15);
			// 生成样式
			Map<String, CellStyle> styles = createStyles(workbook);
			// 创建标题行
			Row row = sheet.createRow(0);
			// 存储标题在Excel文件中的序号
			Map<String, Integer> titleOrder = new HashMap<>();
			for (int i = 0; i < titles.size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(styles.get("header"));
				String title = titles.get(i);
				cell.setCellValue(title);
				titleOrder.put(title, i);
			}
			// 写入正文
			Iterator<Map<String, Object>> iterator = values.iterator();
			// 行号
			int index = 1;
			while (iterator.hasNext()) {
				row = sheet.createRow(index);
				Map<String, Object> value = iterator.next();
				for (Map.Entry<String, Object> map : value.entrySet()) {
					// 获取列名
					String title = map.getKey();
					// 根据列名获取序号
					int i = titleOrder.get(title);
					// 在指定序号处创建cell
					Cell cell = row.createCell(i);
					// 设置cell的样式
					if (index % 2 == 1) {
						cell.setCellStyle(styles.get("cellA"));
					} else {
						cell.setCellStyle(styles.get("cellB"));
					}
					// 获取列的值
					Object object = map.getValue();
					// 判断object的类型
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if (object instanceof Double) {
						cell.setCellValue((Double) object);
					} else if (object instanceof Date) {
						String time = simpleDateFormat.format((Date) object);
						cell.setCellValue(time);
					} else if (object instanceof Calendar) {
						Calendar calendar = (Calendar) object;
						String time = simpleDateFormat.format(calendar.getTime());
						cell.setCellValue(time);
					} else if (object instanceof Boolean) {
						cell.setCellValue((Boolean) object);
					} else {
						if (object != null) {
							cell.setCellValue(object.toString());
						}
					}
				}
				index++;
			}

			try {
				outputStream = new FileOutputStream(filepath);
				workbook.write(outputStream);
				success = true;
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
				if (workbook != null) {
					workbook.close();
				}
			}
			return success;
		}
	}

	/**
	 * 获取后缀
	 * @param filepath filepath 文件全路径
	 */
	private static String getSuffiex(String filepath) {
		if (Strings.isBlank(filepath)) {
			return "";
		}
		int index = filepath.lastIndexOf(".");
		if (index == -1) {
			return "";
		}
		return filepath.substring(index + 1, filepath.length());
	}

	/**
	 * 设置格式
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<>();
		// 标题样式
		XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平对齐
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
		titleStyle.setLocked(true); // 样式锁定
		titleStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBold(true);
		titleFont.setFontName("微软雅黑");
		titleStyle.setFont(titleFont);
		styles.put("title", titleStyle);

		// 文件头样式
		XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // 前景色
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 颜色填充方式
		headerStyle.setWrapText(true);
		headerStyle.setBorderRight(BorderStyle.THIN); // 设置边界
		headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		Font headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		titleFont.setFontName("微软雅黑");
		headerStyle.setFont(headerFont);
		styles.put("header", headerStyle);

		Font cellStyleFont = wb.createFont();
		cellStyleFont.setFontHeightInPoints((short) 12);
		cellStyleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
		cellStyleFont.setFontName("微软雅黑");

		// 正文样式A
		XSSFCellStyle cellStyleA = (XSSFCellStyle) wb.createCellStyle();
		cellStyleA.setAlignment(HorizontalAlignment.CENTER); // 居中设置
		cellStyleA.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyleA.setWrapText(true);
		cellStyleA.setBorderRight(BorderStyle.THIN);
		cellStyleA.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleA.setBorderLeft(BorderStyle.THIN);
		cellStyleA.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleA.setBorderTop(BorderStyle.THIN);
		cellStyleA.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleA.setBorderBottom(BorderStyle.THIN);
		cellStyleA.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleA.setFont(cellStyleFont);
		styles.put("cellA", cellStyleA);

		// 正文样式B:添加前景色为浅黄色
		XSSFCellStyle cellStyleB = (XSSFCellStyle) wb.createCellStyle();
		cellStyleB.setAlignment(HorizontalAlignment.CENTER);
		cellStyleB.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyleB.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		cellStyleB.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyleB.setWrapText(true);
		cellStyleB.setBorderRight(BorderStyle.THIN);
		cellStyleB.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleB.setBorderLeft(BorderStyle.THIN);
		cellStyleB.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleB.setBorderTop(BorderStyle.THIN);
		cellStyleB.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleB.setBorderBottom(BorderStyle.THIN);
		cellStyleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleB.setFont(cellStyleFont);
		styles.put("cellB", cellStyleB);

		return styles;
	}

}

