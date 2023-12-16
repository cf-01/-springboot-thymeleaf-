package com.demo.util;

import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    private SimpleDateFormat dateFormat;

    public StringToDateConverter(String dateFormat) {
        this.dateFormat = new SimpleDateFormat(dateFormat);
    }

    @Override
    public Date convert(String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            // 处理日期转换失败的异常情况
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}
