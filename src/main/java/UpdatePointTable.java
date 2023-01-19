
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




/**
 *
 * @author Oleg
 */
public class UpdatePointTable implements Runnable{

DataBaseTools db= new DataBaseTools("points","pointid");
Random random  = new Random();
int muxn;
double maxC,minC;
private boolean doStop = false;


static int i=0,k=0;


UpdatePointTable(){
}
    
    public synchronized void doStop() { this.doStop = true; }
   
    public synchronized boolean keepRunning() {return this.doStop == false;}

    public synchronized void setMuxNumRows(int nmR){
    this.muxn=nmR;
    }
    
     public synchronized void setRandomBorders(double max, double min){
         this.maxC=max;
         this.minC=min;
     }

    @Override
    public void run() {
       
        while(keepRunning()) {
           
            // keep doing what this thread should do.
             try {
                System.out.println("Running");
                double diff = maxC - minC;
                double x = random.nextDouble(diff + 1);
                double y = random.nextDouble(diff + 1);
                double z = random.nextDouble(diff + 1);
                
                x += minC;
                y += minC;
                z += minC;
            
                db.addData(x, y, z);
                i++;
               if(i>muxn)
                 doStop();
              // Thread.sleep(3L * 1000L);
             }catch (Exception e) {
                System.out.println("Error:"+e);
            }
             
        }
    }

   

    
}
