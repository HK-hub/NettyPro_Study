package com.hk.design.responsibility;

/**
 * @author : HK意境
 * @ClassName : Client
 * @date : 2022/1/27 17:11
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class Client {


    public static void main(String[] args) {




    }


    // 统计字符类型
    public boolean countCharType(String str){

        String ch = "!,./" ;

        for (char c : str.toCharArray()) {
            if(Character.isUpperCase(c)){
                return false ;
            }else if(Character.isDigit(c)){
                return false;
            }else if(!ch.contains(""+c)){
                return false ;
            }
        }

        return true ;

    }


    // 获取一个字符数组中数字的个数
    public boolean countNumber(String str){

        int res = 0 ;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                ++res ;
            }
        }

        return res == 0;

    }

    // 统计连字符个数
    public boolean countLinkChar(String str){

        char[] array = str.toCharArray();
        int res = 0 ;
        int index = -1 ;
        for(int i = 0 ; i < array.length ; ++i)
        {
            if(array[i] == '-'){
                ++res;
                index = i ;
                if(index == 0 || index == array.length -1 ){
                    return false ;
                }
            }
        }

        return res <= 1;

    }


    // 统计标点符号
    public boolean countCharacter(String str){

        int res = 0 ;
        int i = 0 ;
        for (char c : str.toCharArray()) {

            if (c == '!' || c == '.' || c == ',') {
                ++res ;
                if( i != str.length() -1 ) {
                    return false ;
                }
            }
            ++i ;
        }

        return res == 1;


    }


    public int countValidWords(String sentence) {

        String[] strs = sentence.split("\\s+");
        int res = 0;
        for (String str : strs) {

            if(countCharType(str) && countLinkChar(str) && countNumber(str) && countCharacter(str)){
                ++res ;
            }

        }

        return res ;
    }

}
