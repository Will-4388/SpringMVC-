package top.undefinded.domain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author will
 * @date 2020/5/22
 * 自定义字符串转日期：2020-5-20
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String str) {
        DateFormat format = null;
        try {
            if(StringUtils.isEmpty(str)){
                throw new NullPointerException("请输入正确的日期类型");
            }
            format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(str);
            return date;
        }catch (Exception e){
            throw new RuntimeException("输入日期有误");
        }
    }
}
