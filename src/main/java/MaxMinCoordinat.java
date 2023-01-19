/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




/**
 *
 * @author Oleg
 */
public class MaxMinCoordinat implements Runnable,IMaxMinCoordinat{

DataBaseTools db= new DataBaseTools("points","pointid");
 int muxn;
private boolean doStop = false;
private  String typeOperation;

static int i=0,k=0,j=0;
private double x,y,z;
private double searchDistance;

private double outX=0,outY=0,outZ=0;
private static double [][]OutCells;

MaxMinCoordinat(){
    i=0;
    k=0;
    j=0;
   this.muxn = db.muxRows()-1;
   this.OutCells=new double[muxn][4];
}
    @Override
   public void setOperation(String typeOperation){
       this.typeOperation=typeOperation;
        if(typeOperation=="max")
            this.outX=this.outY=this.outZ=0;
         if(typeOperation=="min")
            this.outX=this.outY=this.outZ=10000;
            
   }
    @Override
    public synchronized void doStop() { this.doStop = true; }
    @Override
    public synchronized boolean keepRunning() {return this.doStop == false;}
    @Override
     public synchronized void setPointCoordinat(double x, double y, double z){
       this.x=x;
       this.y=y;
       this.z=z;
    }
   @Override
    public synchronized void searchDistance(double array){
    this.searchDistance=array;
    }
  
    public double[][] getCoordinat(){
      return OutCells;
    }
     public String [] getCoordinatI(int num){
      
      String[] out= new String [4];
      for(int i=0;i<4;i++)
          out[i]=""+OutCells[num][i];
      
      return out;
      
    }
     
      public double [] getPoint(){
      
      double [] out={outX,outY,outZ};

      return out;
  }
     
     public int getRowsCells(){
      return k;
     }


    @Override
    public void run() {
       
      
      
        while(keepRunning()) {
           
            // keep doing what this thread should do.
             try {
                System.out.println("Running");
                double id,tx,ty,tz;
                double temp=Math.abs(Math.sqrt(
                        Math.pow(tx=db.getCellDoubleData(i, 2)-x, 2)
                                +Math.pow(ty=db.getCellDoubleData(i, 3)-y, 2)
                                    +Math.pow(tz=db.getCellDoubleData(i, 4)-z, 2)
                   ));
               
                id=db.getCellDoubleData(i++, 1);
                
               System.out.println("D"+searchDistance+"Temp "+temp);
                  if(temp<=searchDistance){
                  j=0;
                   
                    System.out.println(OutCells[k][j++]=id);
                    System.out.println(OutCells[k][j++]=tx);
                    System.out.println(OutCells[k][j++]=ty);
                    System.out.println(OutCells[k][j]=tz);
                    
                    if(typeOperation=="max")
                         if(Math.abs(tx)>outX && Math.abs(ty)>outY && Math.abs(tz)>outZ){
                             outX=tx;
                            outY=ty;
                            outZ=tz;
                           }
                     if (typeOperation=="min"){
                              if(Math.abs(tx)<=outX && Math.abs(ty)<=outY && Math.abs(tz)<=outZ){
                                  outX=tx;
                                 outY=ty;
                                 outZ=tz;
                           }
                       }
                    
                    
                  k++;
                  
                  }
                  
              
               if(i>=muxn)doStop();
              // Thread.sleep(3L * 1000L);
             }catch (Exception e) {
            
            }
             
        }
    }

   

    
}
