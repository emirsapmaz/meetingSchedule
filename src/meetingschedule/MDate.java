/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingschedule;

/**
 *
 * @author Emir Sapmaz
 */
public class MDate {
    
    private String day,month, year, hour="00", minute="00";
    private final String timeZone="GMT+03:00";
    
    
    public MDate(String dmy){
        day=dmy.charAt(0)+""+dmy.charAt(1);
        month=dmy.charAt(3)+""+dmy.charAt(4);
        year=dmy.charAt(6)+""+dmy.charAt(7)+""+dmy.charAt(8)+""+dmy.charAt(9);
    }

    @Override
    public String toString(){
       return " "+day+"/"+month+"/"+year+" "+timeZone+" "+hour+":"+minute;
    }
 
    
}
