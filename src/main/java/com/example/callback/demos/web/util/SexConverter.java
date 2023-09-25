package com.example.callback.demos.web.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2023-09-25 16:58
 */

public class SexConverter implements Converter<Integer> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Converter.super.supportJavaTypeKey();
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return Converter.super.supportExcelTypeKey();
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String cellValue = cellData.getStringValue();
        if ("男".equals(cellValue)) {
            return 0;
        } else if ("女".equals(cellValue)) {
            return 1;
        }
        return null;
    }

    @Override
    public Integer convertToJavaData(ReadConverterContext<?> context) throws Exception {
        String cellValue = context.getReadCellData().getStringValue();
        if ("男".equals(cellValue)) {
            return 0;
        } else if ("女".equals(cellValue)) {
            return 1;
        }
        return null;

    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 0:男 1:女
        String cellValue = "男";
        if (value == 1) {
            cellValue = "女";
        }
        return new WriteCellData<>(cellValue);
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        // 0:男 1:女
        String cellValue = "男";
        if (context.getValue() == 1) {
            cellValue = "女";
        }
        return new WriteCellData<>(cellValue);

    }
}
