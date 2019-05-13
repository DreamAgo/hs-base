package cn.loverot.base.entity;

public class ResultJson {
    private int code;
    private boolean result;
    private String msg;
    private Object data;

    public ResultJson(){}
    public ResultJson(int code, boolean result) {
        this.code = code;
        this.result = result;

    }
    public ResultJson(int code, boolean result, String msg) {
        this.code = code;
        this.result = result;
        this.msg = msg;

    }
    public ResultJson(int code, boolean result, String msg, Object data) {
        this.code = code;
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
