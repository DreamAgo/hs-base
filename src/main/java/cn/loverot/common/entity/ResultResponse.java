package cn.loverot.common.entity;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author huise
 */
public class ResultResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = -4812885218745484763L;
    public static ResultResponse build(){
        return new ResultResponse();
    }
    public ResultResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public ResultResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public ResultResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public static ResultResponse ok() {
        return build().code(HttpStatus.OK);
    }

    public  static ResultResponse fail() {
        return build().code(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public  static ResultResponse bad() {
        return build().code(HttpStatus.BAD_REQUEST);
    }

    public  static ResultResponse forbid() {
        return build().code(HttpStatus.FORBIDDEN);
    }


    public  static ResultResponse unAuth() {
        return build().code(HttpStatus.UNAUTHORIZED);
    }

    public boolean isSuccess(){
        Object code = get("code");
        if(ObjectUtil.isNotNull(code)&&code.equals(HttpStatus.OK.value())){
            return true;
        }
        return false;
    }

    @Override
    public ResultResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
