package com.example.callback.demos.web.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2023-09-26 10:22
 */
public class ExcelUtil {

    public static Map<String,Map<String,String>> map=new HashMap<>();
    public static final String SEX_CONVERTER ="sexmap";
    public static Map<String,String> sexmap=Map.of("0","男","1","女");
    static {
        map.put("sexMap",sexmap);
    }


    // 泛型方法
    public static <T> void writeExcel(List<T> data, String fileName, Class<T> aClass) {
        List<Object[]> excelData =new ArrayList<>();
        // 将data转换位aClass类型
        ArrayList<String> fieldTypeList = new ArrayList<>();
        Field[] declaredFields = aClass.getDeclaredFields();
        // 获取字段上注解的值  设置表头
        for (Field declaredField : declaredFields) {
            boolean annotationPresent = declaredField.isAnnotationPresent(MyExcelProperty.class);
            if(annotationPresent) {
                MyExcelProperty annotation = declaredField.getAnnotation(MyExcelProperty.class);
                String[] value = annotation.value();
                String s = value[0];
                fieldTypeList.add(s);
            }else {
                fieldTypeList.add(declaredField.getName());
            }
        }
        for (T item : data) {
            // 获取aClass的所有属性
            Object[] objects = new Object[declaredFields.length];
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                declaredField.setAccessible(true);
                try {
                    // 判断是否有注解
                    if (declaredField.isAnnotationPresent(MyExcelProperty.class)) {
                        MyExcelProperty annotation = declaredField.getAnnotation(MyExcelProperty.class);
                        String s = annotation.converterMap();
                        if (map.containsKey(s)){
                            Map<String, String> stringStringMap = map.get(s);
                            String o = declaredField.get(item).toString();
                            objects[i] = stringStringMap.get(o);
                        }else {
                            objects[i] = declaredField.get(item);
                        }
                    } else {
                        objects[i] = declaredField.get(item);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            excelData.add(objects);
        }
        // fieldTypeList 是excel的表头 excelData是excel的数据
        writeExcel(fileName,fieldTypeList,excelData);

    }

    public static void writeExcel(String fileName, List<String> fieldTypeList, List<Object[]> excelData) {
        // 1.创建一个workbook对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 2.创建一个sheet对象
        XSSFSheet sheet = workbook.createSheet("sheet1");
        // 3.创建表头
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < fieldTypeList.size(); i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(fieldTypeList.get(i));
        }
        // 4.遍历数据
        for (int i = 0; i < excelData.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            Object[] objects = excelData.get(i);
            for (int j = 0; j < objects.length; j++) {
                XSSFCell cell = row1.createCell(j);
                cell.setCellValue(objects[j].toString());
            }
        }
        // 5.保存
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName + "test.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
