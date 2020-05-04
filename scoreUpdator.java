

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static javafx.application.Platform.exit;

public class ScoreUpdator 
{
    public static void main(String[] args) 
    {
       Q q=new Q();
       new server(q);
     new client(q);
    }
    
}
class Q
{
    boolean valueset=false;
     private int score,Rand1;
    public synchronized void put(int score,int Rand1)
    {
        while(valueset)
        {
            try{
                wait();
            }
            catch(Exception e)
            {
                
            }
        }
       // System.out.println("PUT:"+score);
        this.score=score;
        this.Rand1=Rand1;
         valueset=false;
        notify();
        
    }
    public synchronized void get()
    {
          while(!valueset)
        {
            try{
                wait();
            }
            catch(Exception e)
            {
                
            }
            System.out.println("this ball score:"+Rand1);
             System.out.println("Score:"+score);
             
        }
       
         valueset=true;
        notifyAll();
    }

   
}

class server implements Runnable
{
    Q q;
    Scanner sc=new Scanner(System.in);
    server(Q q)
    {
        this.q=q;
         Thread t1=new Thread(this,"client");
         t1.start();
    }
   public void run()
   {
        int score=0,noball=0,out=0,wide=0;
       for(int i=0;i<300;i++)
       {
         ArrayList<Integer> al=new ArrayList<>();
         int Rand1=((int)(Math.random() *10))%7;
                 
         
         al.add(Rand1);
           if(Rand1==0)
           
           {
               int Rand2=((int)(Math.random() *10))%4;
              
               System.out.println("0.Noball 1.Out 2.wide 3.Nothing");
             
               switch(Rand2)
               {
                   case 1:
                             noball++;
                            break;
                   case 2:
                            out++; 
                            break;
                   case 3:
                            wide++;
                            score=score+1;
                            i=i+1;
                            break;
                   case 4:
                            break;
               }
               if(out==10)
                   break;
            }
           
           else{
           score=score+Rand1;
            q.put(score,Rand1);
            try{
                Thread.sleep(1000);
                
            }
            catch(InterruptedException e)
            {
                
            }
       }
           System.out.println("OUT="+out);
           System.out.println("Wide="+wide);
           System.out.println("No ball="+noball);
           
       } 
       }

    
   }


class client implements Runnable
{
    Q q;
    Scanner sc=new Scanner(System.in);
    
    client()
    {
        
    }
    client(Q q)
    {
        this.q=q;
        Thread t1=new Thread(this,"client");
        t1.start();
    }
    public void run()
    {
        for(int i=0;i<360;i++)
       {
           q.get();
           try{
                Thread.sleep(500);
                
            }
            catch(InterruptedException e)
            {
                
            }
       }
        
    }
}
/*Output:
OUT=0
Wide=0
No ball=0
this ball score:6
Score:10
OUT=0
Wide=0
No ball=0
this ball score:1
Score:11
OUT=0
Wide=0
No ball=0
this ball score:1
Score:12
OUT=0
Wide=0
No ball=0
this ball score:2
Score:14
OUT=0
Wide=0
No ball=0
this ball score:3
Score:17
OUT=0
Wide=0
No ball=0
this ball score:5
Score:22
OUT=0
Wide=0
No ball=0
this ball score:2
Score:24
OUT=0
Wide=0
No ball=0
this ball score:2
Score:26
OUT=0
Wide=0
No ball=0
this ball score:4
Score:30
OUT=0
Wide=0
No ball=0
this ball score:5
Score:35
OUT=0
Wide=0
No ball=0
this ball score:3
Score:38
OUT=0
Wide=0
No ball=0
0.Noball 1.Out 2.wide 3.Nothing
OUT=0
Wide=0
No ball=0
this ball score:2
Score:40
OUT=0
Wide=0
No ball=0
0.Noball 1.Out 2.wide 3.Nothing
OUT=0
Wide=0
No ball=0
this ball score:6
*/