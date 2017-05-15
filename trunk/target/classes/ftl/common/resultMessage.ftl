package ${projStructurePath}.util.common;

import java.io.Serializable;

public class ResultMessage implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public final Integer sucess=1;
    public final Integer fail=0;
    //结果
    private Integer result=sucess;
    //数据/异常信息
    private Object data;
    
    public ResultMessage(Object data){
        this.data=data;
    }
    public ResultMessage(Integer result,Object data){
        this.result=result;
        this.data=data;
    }
    
    public Integer getResult() {
        return result;
    }
    public void setResult(Integer result) {
        this.result = result;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    
    
}
