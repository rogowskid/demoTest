package ArrayInMinusValue;

public class Main  extends Thread{

    private int tab[] = {4,3,2,-1,5,-1,-4,8};
    private int sum=0;


    Thread t1 = new Thread(() -> {


        for (int i = 0 ; i< tab.length; i++)
        {
            if(tab[i]<0)
            {
                System.out.println(tab[i]);
            }
            else
            {
                sum+=i;
            }
        }


    });

    Thread t2 = new Thread(() -> {

        System.out.println(sum);
    });




    public static void main(String[] args) throws InterruptedException {

        Main main = new Main();



        main.t1.start();
        main.t1.join();
        main.t2.start();
    }




}
