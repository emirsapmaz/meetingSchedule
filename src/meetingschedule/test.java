/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingschedule;

import java.util.ArrayList;
import java.util.Scanner;
import meetingschedule.Meeting;
/**
 *
 * @author Emir Sapmaz
 */
public class test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Meeting> allMeetings = new ArrayList<>();
        ArrayList<Person> users = new ArrayList<>();
        Person host=null,currentUser=null;
        System.out.println("Create a list of users, press -1 to continue with menu");
        
        int determine=0;
        do{
        System.out.println("Enter username");
        if(input.hasNextInt()&& input.nextInt()==-1){
            determine=-1;
        }
        else{
            Person u = new Person(input.next());
            users.add(u);
        }
        }while(determine!=-1);
        
        int menu;
        do{
            System.out.println(         "*********************************************\n"+
                                        "0. Login\n" +
                                        "1. Create/Host new Meeting\n" +
                                        "2. Cancel a meeting\n" +
                                        "3. Attend an existing meeting\n" +
                                        "4. Leave a meeting\n" +
                                        "5. Display My Meetings\n" +
                                        "6. Display Meetings organized by me\n" +
                                        "7. Logout\n" +
                                        "8. Exit\n"+
                                        "*********************************************");
            menu = input.nextInt();
            System.out.println("Your choice: "+menu);
            switch(menu){
                        case 0:
                            System.out.println("Enter user name to login: ");
                            host=new Person(input.next());
                            currentUser =users.get(checkPerson(users, host));
                            System.out.println("Logged in as "+currentUser.getName()+" successfully");
                            break;
                        case 1:
                            Meeting createdMeet=createMeeting(currentUser);
                            allMeetings.add(createdMeet);
                            break;
                        case 2:
                            if(!currentUser.getMyMeetings().isEmpty() || !currentUser.getiOrganize().isEmpty())
                                cancelMeeting(currentUser, allMeetings);
                            else{
                                System.out.println("Your agenda is empty.");
                            }
                            break;
                        case 3:
                            if(allMeetings.size()==0)
                                System.out.println("No meetings created.");
                            else
                                attendMeeting(allMeetings,currentUser);                                 
                            break;
                        case 4:
                            if(currentUser.getMyMeetings().isEmpty())              
                                System.out.println("You can't leave any events if you are not attending any");
                            else
                                leaveMeeting(allMeetings, currentUser);                               
                            break;
                        case 5:
                            if(currentUser.getMyMeetings().isEmpty())            
                                System.out.println("Your agenda is empty.");
                            else{
                                System.out.println("My meetings:");
                                currentUser.displayMyMeetings();
                            }
                            break;
                        case 6:
                            if(currentUser.getiOrganize().isEmpty())
                                System.out.println("You havent organized any meeting yet.");
                            else{
                                System.out.println("Meetings that I have organized:");
                                currentUser.displayMyOrganizations();
                            }
                            break;
                        case 7:
                            System.out.println("Logged off as "+currentUser.getName());
                            break;
                        case 8:
                            System.out.println("Bye bye!");
                            break;
                        default:
                            System.out.println("Enter the numbers given in the menu.");
                            break;
                            
            }
        }while(menu!=8);
            
    }
    public static int checkPerson(ArrayList<Person> user,Person host){
        for (int i = 0; i < user.size(); i++) {
            if(user.get(i).equals(host))
                return i;
        }
        return -1;
    }
    
    public static int checkMeet(ArrayList<Meeting> allM,String name){
        for (int i = 0; i < allM.size(); i++) {
            if(allM.get(i).getMname().equals(name))
                return i;
        }
        return -1;
    }
    
    public static Meeting createMeeting(Person host){
        Scanner input = new Scanner(System.in);
        host.setHostTrue();
        System.out.println("Enter the name of the meeting: ");
        String nameOfMeeting = input.next();
        System.out.println("Enter the date of the meeting: (dd/mm/yyyy)");
        String datee=input.next();
        Meeting meeting = null;
        MDate date=null;
        if(datee.length()!=10){
            System.out.println(nameOfMeeting+" couldn't be created, please enter the date as shown.");
        }else
            date = new MDate(datee);
        meeting = new Meeting(nameOfMeeting,host,datee);
        host.organizeMeeting(meeting);
        host.addMeeting(meeting);
        System.out.println(nameOfMeeting+date.toString()+" was created successfully.");
        return meeting;
    }
    
    public static void cancelMeeting(Person host,ArrayList<Meeting> allM){
        Scanner input = new Scanner(System.in);
        System.out.println("*****************************************");
        if(host.getMyMeetings().isEmpty())
            host.displayMyOrganizations();
        else
            host.displayMyMeetings();
        System.out.println("*****************************************");
        System.out.println("Enter meeting name to be cancelled:");
        String name = input.next();
        Meeting event =allM.get(checkMeet(allM, name));
        if(event.getHost()==host){
            allM.remove(checkMeet(allM, name));
            host.removeMeeting(event);
            host.cancelMeeting(event);
            event.removeAllAttendees(event);
            System.out.println("All attendees of "+ name +" have been removed and "+name+" is cancelled.");
        }else
            System.out.println("You are not the host of this meeting!");
    }
    
    public static void attendMeeting(ArrayList<Meeting> allM,Person user){
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < allM.size(); i++) {
            System.out.println(allM.get(i).getMname()+allM.get(i).getDate());
        }
        System.out.println("Enter an event name that you want to attend: ");
        String name = input.next();
        Meeting event = null;
        if(checkMeet(allM, name)==-1)
            System.out.println("Non-existing event name entered, please try again.");
        else{
            event =allM.get(checkMeet(allM, name));
            event.addAttendee(user);
            System.out.println("Ok, the meeting has been added to your agenda.");
        }
        
    }
    
    public static void leaveMeeting(ArrayList<Meeting> allM,Person user){
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < user.getMyMeetings().size(); i++) {
            System.out.println(user.getMyMeetings().get(i).getMname()+user.getMyMeetings().get(i).getDate());
        }
        System.out.println("Enter the name of the event that you want to leave:");
        String name = input.next();
        Meeting event =allM.get(checkMeet(allM, name));
        if(checkMeet(allM, name)==-1)
            System.out.println("Invalid event name, please try again.");
        else{
            event.removeAttendee(user);
            System.out.println("You have successfully left the event called "+name);
        }
    }
    
    
    
}
