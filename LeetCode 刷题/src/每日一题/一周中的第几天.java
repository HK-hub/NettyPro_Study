package 每日一题;


import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/***
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 *
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 *
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-week
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class 一周中的第几天 {


    public static void main(String[] args) {

        String[] week ={"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar instance = Calendar.getInstance();
        instance.set(1993, 8-1,15);
        System.out.println(instance.get(Calendar.DATE));
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.YEAR));
        System.out.println(instance.get(Calendar.DAY_OF_WEEK));
        System.out.println(week[instance.get(Calendar.DAY_OF_WEEK) - 1]);
        System.out.println("++++++++++++++++++++++++");


    }



}
