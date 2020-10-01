package com.example.demo.bean;

/*
 * @ClassName : ResultDto
 * @Description : TODO
 * @author zzh
 * @create 2019/4/5
 * @version V1.0
 */

public class ResultDto<T> {
    private String code;
    private String msg;

    private T data;

    public ResultDto() {
        this.code = "0";
        this.msg = "成功";
    }

    public ResultDto(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResultDto {\n");
        sb.append("    code: ").append(this.toIndentedString(this.code)).append("\n");
        sb.append("    msg: ").append(this.toIndentedString(this.msg)).append("\n");
        sb.append("    data: ").append(this.toIndentedString(this.data)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
