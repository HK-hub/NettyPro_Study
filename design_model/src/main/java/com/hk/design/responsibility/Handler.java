package com.hk.design.responsibility;

/**
 * @author : HK意境
 * @ClassName : Handler
 * @date : 2022/1/27 17:02
 * @description : 抽象处理者类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public abstract class Handler {


    public static final int ONE_DAY = 1 ;

    public static final int THREE_DAYS = 3 ;
    public static final int SENVEN_DAYS = 7 ;


    // 处理请假请求
    public abstract boolean handleRequest(LeaveRequest request);


    // 提交给下一个领导审批
    public boolean submitRequest(LeaveRequest request){

        // 该领导进行审批
        boolean res = this.handleRequest(request) ;
        boolean nextRes = true ;
        // 下一个部门审批
        if(this.nextHandler != null && this.endTime < request.getDays()){
           nextRes = this.nextHandler.submitRequest(request) ;
        }else{
            System.out.println("审批流程结束");
        }

        return res && nextRes ;
    }

    // 声明，可以处理的天数范围
    protected int startTime ;
    protected int endTime ;


    // 声明后继处理类(上级领导)
    protected Handler nextHandler ;




    public Handler(int startTime){
        this.startTime = startTime ;
    }

    public Handler(int s ,int e){
        this.startTime = s ;
        this.endTime = e ;
    }


}
