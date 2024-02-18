
package meetingschedule;

import java.util.ArrayList;

/**
 *
 * @author Emir Sapmaz
 */
public class Person {
    
    private final String name;
    private static int count;
    private int id=1000;
    private ArrayList<Meeting> myMeetings;
    private ArrayList<Meeting> iOrganize;
    private boolean isHost = false;
    
    public Person(String name){
        this.name=name;
        id+=count;
        myMeetings = new ArrayList<>();
        iOrganize = new ArrayList<>();
        count++;
    }
    
    public boolean equals(Person other){
        if(name.equals(other.name) || id==other.id)
            return true;
        else
            return false;
    }
    public boolean addMeeting(Meeting meeting){
        if(!meeting.equals(meeting)){
            myMeetings.add(meeting);
            return true;
        }else
            return false;   
    }
    public void removeMeeting(Meeting meeting){
        myMeetings.remove(meeting);
        int depo =meeting.getAttendees().size();
        for (int i = 0; meeting.getAttendees().size()==0;) {
            meeting.getAttendees().remove(i);
        }
    }
    public void organizeMeeting(Meeting meeting){
        iOrganize.add(meeting);
    }
    public void cancelMeeting(Meeting meeting){                            
        iOrganize.remove(meeting);
        for (int i = 0; i < meeting.getAttendees().size(); i++) {
            meeting.getAttendees().get(i).removeMeeting(meeting);
        }
    }
    public void displayMyMeetings(){
        for (int i = 0; i < myMeetings.size(); i++) {
            System.out.println("Name: "+myMeetings.get(i).getMname()+" Meeting dates:"+myMeetings.get(i).getDate()+" Host: "+myMeetings.get(i).getHost().name);
        }
    }
    public void displayMyOrganizations(){
        for (int i = 0; i < iOrganize.size(); i++) {
            System.out.println(iOrganize.get(i).getMname()+iOrganize.get(i).getDate());
        }
    }
    @Override
    public String toString(){
        return "User "+name+" with ID "+id+" has "+myMeetings.size()+" meetings to attend and "+iOrganize.size()+" meetings organized.\n" ;
    }
    
    public void setHostTrue() {
        isHost=true;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Meeting> getMyMeetings() {
        return myMeetings;
    }

    public ArrayList<Meeting> getiOrganize() {
        return iOrganize;
    }
   
}
