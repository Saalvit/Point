/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Oleg
 */
public interface IMaxMinCoordinat{
    public void doStop();
    public boolean keepRunning();
    public  void setPointCoordinat(double x, double y, double z);
    public  void searchDistance(double array);
    public void setOperation(String typeOperation);
    
}
