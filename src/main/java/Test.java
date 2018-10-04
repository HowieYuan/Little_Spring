/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-03
 * @Time 21:23
 */
public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(101/0);

        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println(1);
            return;
        } finally {
            System.out.println(2);
        }
        System.out.println(3);
    }
}
