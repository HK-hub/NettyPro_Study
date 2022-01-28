package 每日一题;

import java.util.Stack;

public class 简化路径 {

    public static String simplifyPath(String path) {

        StringBuilder pathBuilder = new StringBuilder(path);

        // 去除尾部 /
        if(path.lastIndexOf("/") == path.length() -1){
            path = path.substring(0,path.lastIndexOf("/"));
        }

        Stack<String> stringStack = new Stack<>();
        stringStack.push("/") ;
        // 替换. 2个或多个的“/” 替换为 一个“/”
        path = path.replaceAll("/{2,}", "/");

        // 切割
        String[] split = path.split("/");

        for (int i = 0; i < split.length; i++) {
            if (".".equals(split[i]) || "/".equals(split[i])){
                continue;
            }else if ("..".equals(split[i])){
                if(stringStack.size()>= 1){
                    stringStack.pop() ;
                }

            }else {
                // 添加
                if(split[i].length() > 0){
                    stringStack.push(split[i]);
                }

            }
        }

        StringBuilder res = new StringBuilder("/");

        for (String s : stringStack) {
            res.append(s+"/") ;
        }

        String rr = res.toString();
        rr = rr.replaceAll("/{2,}", "/");

        // 去除尾部 /
        if(rr.lastIndexOf("/") == rr.length() -1 && rr.length() >=2 ){
            rr = rr.substring(0,rr.lastIndexOf("/"));
        }

        return rr ;
    }

    public static void main(String[] args) {

        simplifyPath("/home/");
        

    }




}
