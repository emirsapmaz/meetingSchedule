/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingschedule;

import java.util.ArrayList;

/**
 *
 * @author Emir Sapmaz
 */
public class Meeting {
    
    private String Mname;
    private MDate date;
    private ArrayList<Person> attendees;
    private final Person host;
    private boolean isOnline;
    private String url;
    private String location="AMF-318";
    
    public Meeting(String Mname,Person host,String datee){
        this.Mname=Mname;
        attendees = new ArrayList<>();
        this.host=host;
        date = new MDate(datee);
        if(isOnline)
            url="URL will be generated for online meetings";
       attendees.add(host);
    }
    public boolean equals(Meeting other){
        if(date.toString()==other.date.toString() && host==other.host && attendees==other.attendees )
            return true;
        else
            return false;
    }
    public void addAttendee(Person a){
        if(!attendees.contains(a))
            if(a.addMeeting(this))
                attendees.add(a);
    }
    public boolean removeAttendee(Person a){
        if(attendees.contains(a)){
            a.removeMeeting(this);
            return true;
        }else
            return false;
    }
    public void removeAllAttendees(Meeting event){
        event.attendees.clear();
        for (int i = 0; i < event.getAttendees().size(); i++) {
            event.getAttendees().get(i).removeMeeting(event);
        }
    }
    public MDate getDate() {
        return date;
    }
    public Person getHost() {
        return host;
    }

    public String getMname() {
        return Mname;
    }

    public ArrayList<Person> getAttendees() {
        return attendees;
    }
    
    
    @Override
    public String toString(){
        if(isOnline)
            for (int i = 0; i < attendees.size(); i++) {
                return "Host: "+host.getName()+" Name: "+Mname+"Date:"+date.toString()+" Online"+"\nList of attendees: "+attendees.get(i).getName();
            }
        else
            for (int i = 0; i < attendees.size(); i++) {
                return "Host: "+host.getName()+" Name: "+Mname+"Date:"+date.toString()+" Location: "+location+"\nList of attendees: "+attendees.get(i).getName();
            }
        return null;        
    }


}
