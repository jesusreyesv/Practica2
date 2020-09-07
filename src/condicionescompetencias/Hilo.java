
package condicionescompetencias;
import javax.swing.*;
public class Hilo extends Thread{
    private JTextArea area;
    private rCompartido rc;
    
    private boolean pausa;
    private boolean stop;
    
    Hilo(JTextArea area, rCompartido rc){
        this.area=area;
        this.rc=rc;
    }
    
    
    public void run(){
        while(!stop){
            try{
                
                synchronized(this){
                    if (pausa){
                        wait();
                    }
                }
                rc.setDatoCompartido(this.getName());
                area.append(rc.getDatoCompartido()+"\n");
                sleep(1500);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
        
    }
    
    public synchronized void pausar(){
        pausa = true;
    }
    
    public synchronized void reanudar(){
        pausa = false;
        this.notify();
    }
    
    public synchronized void detenerse(){
        stop = true;
    } 
}
