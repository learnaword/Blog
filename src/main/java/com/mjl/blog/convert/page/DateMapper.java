package com.mjl.blog.convert.page;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateMapper {

    @Named("timestampToString")
    public String timestampToString(Long timestamp){
        Date date = new Date(timestamp); // 需要将秒数转换为毫秒数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
