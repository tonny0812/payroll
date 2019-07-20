package com.wage.util;

import com.wage.annotation.EntityColumn;
import com.wage.annotation.ExcelEntity;
import com.wage.contants.ColumnType;
import com.wage.model.Employee2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelEntityUtil {

    private static Logger logger  = LoggerFactory.getLogger(ExcelEntityUtil.class);

    private static Map<Class<?>, EntityDesc> entityDescMap = new HashMap<>();

    private static class EntityColumnDesc {
        String name;
        ColumnType columnType;
        Method setter;
    }

    private static class EntityDesc {
        Map<String, EntityColumnDesc> columnDescMap;
    }

    /**
     * 获取缓存中的EntityDesc
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    private static EntityDesc getEntityDesc(Class<?> clazz) throws Exception {
        EntityDesc entityDesc = entityDescMap.get(clazz);
        if (entityDesc == null) {
            ExcelEntity entityAnnotation = clazz.getAnnotation(ExcelEntity.class);
            if (entityAnnotation == null) {
                String errorMsg = "未加@ExcelEntity注解的类型：" + clazz.getName() + "，解析失败";
                logger.error(errorMsg);
                throw new Exception(errorMsg);
            }

            entityDesc = new EntityDesc();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                EntityColumn columnAnnotation = field.getAnnotation(EntityColumn.class);
                if (columnAnnotation == null) {
                    continue;
                }

                if(!columnAnnotation.id()) {
                    EntityColumnDesc columnDesc = new EntityColumnDesc();
                    columnDesc.name = columnAnnotation.name();
                    columnDesc.columnType = columnAnnotation.type();
                    columnDesc.setter = getSetter(clazz, field);

                    if(entityDesc.columnDescMap == null) {
                        entityDesc.columnDescMap = new HashMap<>();
                    }
                    entityDesc.columnDescMap.put(columnDesc.name, columnDesc);
                }
            }

            entityDescMap.put(clazz, entityDesc);
        }
        return entityDesc;
    }

    /**
     * 获取指定属性的getter方法
     *
     * @param clazz
     * @param field
     * @return
     * @throws Exception
     */
    private static Method getGetter(Class<?> clazz, Field field) throws Exception {
        Method met = clazz.getMethod("get" + initStr(field.getName()));
        return met;
    }

    /**
     * 获取指定属性的setter方法
     *
     * @param clazz
     * @param field
     * @return
     * @throws Exception
     */
    private static Method getSetter(Class<?> clazz, Field field) throws Exception {
        Method met = clazz.getMethod("set" + initStr(field.getName()), field.getType());
        return met;
    }


    /**
     * 将单词的首字母大写
     *
     * @param old
     * @return
     */
    private static String initStr(String old) {
        String str = old.substring(0, 1).toUpperCase() + old.substring(1);
        return str;
    }

    /**
     * 通用的解析Hive表记录的方法
     *
     * @param record
     * @return
     */
    public static <T> T parse(ExcelRowRecord record, Class<T> clazz) throws Exception {

        if (record == null || record.getCellMap().isEmpty()) {
            String errorMsg = "空记录" + clazz.getName() + "，无法解析";
            logger.error(errorMsg);
            throw new Exception(errorMsg);
        }

        EntityDesc tableDesc = getEntityDesc(clazz);
        T result = clazz.newInstance();
        for (EntityColumnDesc columnDesc : tableDesc.columnDescMap.values()) {
            if(record.getCellMap().containsKey(columnDesc.name)) {
                Object value = record.getCellMap().get(columnDesc.name);
                if(null != value) {
                    Method setterMethod = columnDesc.setter;
                    setterMethod.invoke(result, value);
                }
            }
        }
        return result;
    }

    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook wb = null;
        if(excelFilePath==null){
            logger.error("Excel文件名为空");
        } else {
            String lastName = excelFilePath.substring(excelFilePath.lastIndexOf("."));
            InputStream is = new FileInputStream(excelFilePath);
            if (".xls".equals(lastName)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(lastName)) {
                wb = new XSSFWorkbook(is);
            }
        }
        return wb;
    }

    private static ExcelTitle getExcelTtiles(String excelFilePath, int sheetIndex){
        ExcelTitle title = new ExcelTitle();
        Workbook wb = null;
        try {
            wb = getWorkbook(excelFilePath);
            if (null != wb) {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                if(null != sheet) {
                    //总行数
                    int rowNum = sheet.getLastRowNum();
                    Row row = sheet.getRow(0);
                    if(null != row) {
                        //得到每一行单元格个数，包括其中的空单元格，这里要求表格内容的每一行都是固定的个数
                        int colNum = row.getLastCellNum();  //每行单元格总数
                        for(int j=0;j<colNum;j++) {
                            String t = (String) getCellFormatValue(row.getCell(j), ColumnType.STRING);
                            if(null != t && "" != t) {
                                title.getTitleMap().put(j, t);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("获取表头出错", e);
        }

        return title;
    }

    private static <T> List<T> getExcelEntities(String excelFilePath, int sheetIndex, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        ExcelTitle excelTitle = ExcelEntityUtil.getExcelTtiles(excelFilePath, sheetIndex);
        EntityDesc tableDesc = getEntityDesc(clazz);
        if(!excelTitle.getTitleMap().isEmpty()) {
            Map<Integer, String> titleMap = excelTitle.getTitleMap();

            Workbook wb = null;
            try {
                wb = getWorkbook(excelFilePath);
                if (null != wb) {
                    Sheet sheet = wb.getSheetAt(sheetIndex);
                    if(null != sheet) {
                        //总行数
                        int rowNum = sheet.getLastRowNum();
                        if(rowNum > 1) {
                            Row row = sheet.getRow(0);//表头
                            if(null != row) {
                                //得到每一行单元格个数，包括其中的空单元格，这里要求表格内容的每一行都是固定的个数
                                int colNum = row.getLastCellNum();  //每行单元格总数
                                for(int i=1; i<=rowNum; i++) {
                                    ExcelRowRecord record = new ExcelRowRecord();
                                    row = sheet.getRow(i);
                                    int j = 0;
                                    while (j < colNum) {
                                        String title = titleMap.get(j);
                                        if(null != title && "" != title) {
                                            EntityColumnDesc columnDesc = tableDesc.columnDescMap.get(title);
                                            if(null != columnDesc) {
                                                record.getCellMap().put(title, getCellFormatValue(row.getCell(j), columnDesc.columnType));
                                            }
                                        }
                                        j++;
                                    }
                                    T vrecord = parse(record, clazz);
                                    list.add(vrecord);
                                }
                            }
                        } else {
                            logger.error("Excel表格无数据");
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("获取表头出错", e);
            }
        }

        return list;
    }

    private static Object getCellFormatValue(Cell cell, ColumnType columnType) {
        Object result = null;
        if (cell == null || cell.equals("") || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return result;
        }
        switch (columnType) {
            case STRING:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                result = cell.getRichStringCellValue().getString();
                break;
            case LONG:
                try {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    result = Long.valueOf(cell.getRichStringCellValue().getString());
                } catch (Exception e) {
                    logger.error("转化LONG出错：",e);
                }
                break;
            case INTEGER:
                try {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    result = Integer.valueOf(cell.getRichStringCellValue().getString());
                } catch (Exception e) {
                    logger.error("转化INTEGER出错：",e);
                }
                break;
            case DOUBLE:
                try {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    result = Double.parseDouble(cell.getRichStringCellValue().getString());
                } catch (Exception e) {
                    logger.error("转化LONG出错：",e);
                }
                break;
            case DATE:
                // 判断当前的cell是否为Date
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    result = date;
                }
                break;
            case BYTE:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                result = Byte.parseByte(cell.getStringCellValue());
                break;
            default:
                logger.warn("未知数据类型：" + columnType.name());
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\qiuqiu\\Desktop\\temp\\test.xlsx";
//        ExcelTitle excelTitle = ExcelEntityUtil.getExcelTtiles(path, 0);
//        Map<Integer, String> map = excelTitle.getTitleMap();
//        for(Integer key : map.keySet()) {
//            System.out.println(key + ":" + map.get(key));
//        }

        try {
            List<Employee2> list = ExcelEntityUtil.getExcelEntities(path, 0, Employee2.class);
            for(Employee2 employee : list) {
                System.out.println(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/**
 * Excel标题
 */
class ExcelTitle {
    Map<Integer,String> titleMap = new HashMap<>();

    public Map<Integer,String> getTitleMap() {
        return titleMap;
    }
}

/**
 * Excel一行内容
 */
class ExcelRowRecord {
    Map<String, Object> cellMap = new HashMap<>();

    public Map<String, Object> getCellMap() {
        return cellMap;
    }
}
