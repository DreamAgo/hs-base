package cn.loverot.common.converter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.wuwenze.poi.convert.WriteConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Execl导出时间类型字段格式化
 *
 * @author huise
 */
@Slf4j
public class TimeConverter implements WriteConverter {
    @Override
    public String convert(Object value) {
        if (ObjectUtil.isNotNull(value)){
            return DateUtil.formatDateTime((Date) value);
        }
        return "";

    }
}
