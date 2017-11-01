import java.util.Scanner;

class SeatReservation   //seat reservation class
{
    public static void main(String[] args)    //main method to call all the threads and method defined
    {
        Reservation reserve = new Reservation(); // LINE A     //created object of class reservation
        Person thread1 = new Person(reserve, 5); // LINE B     //Calling thread of class person
        thread1.start(); 			//First thread starts
        Person thread2 = new Person(reserve, 4);    //Numbe of seat reserved
        thread2.start();     //Second thread starts
        Person thread3 = new Person(reserve, 2);
        thread3.start();			//Third thread starts
    }
}

class Reservation    //PErfomring the reservation activity
{
 
    static int availableSeats = 10;    //initial  seat available are 10;

    synchronized void reserveSeat(int requestedSeats) // LINE D
    {
        System.out.println(Thread.currentThread().getName() + " entered.");
        System.out.println("Availableseats : " + availableSeats + " Requestedsetas : " + requestedSeats);
        if (availableSeats >= requestedSeats)    //checking availability of seats and reserving on basis of that only
        {
            System.out.println("Seat Available. Reserve now :-)");    //prints if seats are available
            try				//try block to check if there is any exception or not
            { 
                Thread.sleep(100);     // LINE E			thread went in sleep
            }
            catch (InterruptedException e)    //catch any exception thrown by try block
            {
                System.out.println("Thread interrupted");
            }
            System.out.println(requestedSeats + " seats reserved.");    //if available print reserved
            availableSeats = availableSeats - requestedSeats;    //reduce the number of available seat
        }
        else
        {
            System.out.println("Requested seats not available :-(");    //else no seat available
        }
        System.out.println(Thread.currentThread().getName() + " leaving.");    //exit the thread
        System.out.println("----------------------------------------------"); 
    }
}

class Person extends Thread
{

    Reservation reserve;     //object of class reservation
    int requestedSeats;

    public Person(Reservation reserve, int requestedSeats)
    {
        this.reserve = reserve;    //calling the  instance variable of the clas
        this.requestedSeats = requestedSeats;   //First thread starts
    }

    @Override
    public void run() // LINE C    
    {
        reserve.reserveSeat(requestedSeats);   //runign the thread using run method
    }
}