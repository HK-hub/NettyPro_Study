package com.hk.design.responsibility;

/**
 * @author : HK意境
 * @ClassName : GroupLeader
 * @date : 2022/1/27 17:23
 * @description : 组长类：具体处理者
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class Manager extends Handler{


    public Manager(){
        super(1 ,Handler.THREE_DAYS) ;

    }


    @Override
    public boolean handleRequest(LeaveRequest request) {

        if (request.getDays() <= this.endTime){
            System.out.println(this.getClass().getName());

            System.out.println(request.getName() + ": " + request.getCause() + " : " + "请假：" + request.getDays() + "审批通过");
            return true ;
        }else{
            System.out.println("无权审核!");
            return false ;
        }

    }
}
