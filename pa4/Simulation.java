// Christopher Griffis <chdgriff>
// CS12B - 02/26/18
// QueueTest.java - Test Queue ADT
import java.io.*;
import java.util.Scanner;


public class Simulation {
    public static final int UNDEF = -1;
    // Uses getJob() and outputs an array of jobs from file
    public static Job[] readInputFile(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        int numJobs = in.nextInt();
        Job[] jobs = new Job[numJobs];
        
        for (int i = 0; i < numJobs; i++) {
            jobs[i] = getJob(in);
        }
        in.close();
        return jobs;
    }
    
    // Returns next job from scanner
    public static Job getJob(Scanner in) {
        int a = in.nextInt();
        int d = in.nextInt();
        return new Job(a, d);
    }
    
    // Clears all queues from queue[]
    public static void resetProcessors(Queue[] queue) {
        for (Queue i : queue) if (!i.isEmpty()) i.dequeueAll();
    }
    
    // Populates processor 0 with jobs
    public static void resetStorage(Job[] jobs, Queue queue) {
        for (int i = 0; i < jobs.length; i++) {
            jobs[i].resetFinishTime();
            queue.enqueue(jobs[i]);
        }
    }
    
    // Returns index of shortest processor up to given num
    public static int getShortest(Queue[] queue, int num) {
        int shortest = 1;
        int sizeShortest = queue[1].length();
        for (int i = 2; i <= num; i++) {
            if (sizeShortest == 0) return shortest;
            else if (queue[i].length() < sizeShortest) {
                shortest = i;
                sizeShortest = queue[i].length();
            }
        }
        return shortest;
    }
    
    // Returns next time given processors and number of processors
    public static int getNextTime(Queue[] queue, int num) {
        int time = 0;
        boolean setTime = false;
        for (int i = 0; i <= num; i++) {
            if (!(queue[i].isEmpty())) {
                // Sets initial time
                if (!setTime) {
                    if (i == 0 && ((Job) queue[i].peek()).getFinish() == UNDEF) {
                        time = ((Job) queue[i].peek()).getArrival();
                        setTime = true;
                    }
                    else if (i != 0) {
                        time = ((Job) queue[i].peek()).getFinish();
                        setTime = true;
                    }
                }
                // Compares and finds if shorter time
                else {
                    if (((Job) queue[i].peek()).getFinish() < time) {
                        time = ((Job) queue[i].peek()).getFinish();
                    }
                }
            }
        }
        return time;
    }
    
    public static String processorsOut(Queue[] queue, int num) {
        StringBuffer sb = new StringBuffer();
        sb.append(0 + ":" + ((queue[0].length() == 0) ? "":" ") + queue[0]);
        for (int i = 1; i <= num; i++) {
            sb.append("\n" + i + ":" + ((queue[i].length() == 0) ? "":" ") + queue[i]);
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Error, Usage: Simulation <input file>");
            System.exit(1);
        }
        
        Job[] backup; // Holds jobs in sorted order
        Queue[] processors; // Holds all processors and their queues
        int numJobs; // Number of jobs
        
        PrintWriter trace;
        PrintWriter report;
        Scanner in = new Scanner(System.in);
        
        
        backup = readInputFile(args[0]); // Populates backup with jobs
        numJobs = backup.length; // Sets number of jobs
        processors = new Queue[numJobs+1]; // Creates amount of processors
        
        for (int i = 0; i < processors.length; i++) processors[i] = new Queue();
        
        resetStorage(backup, processors[0]);
        

        trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
        report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
        
        // Writes header to trace
        trace.println("Trace file: " + args[0] + ".trc");
        //trace.println(numJobs + " Job" + ((numJobs==1) ? "":"s") + ":\n" + processors[0] + "\n"); // Fixes plural mistakes
        trace.println(numJobs + " Jobs:\n" + processors[0] + "\n");
        /*System.out.println("Trace file: " + args[0] + ".trc");
        System.out.println(numJobs + " Job" + ((numJobs==1) ? "":"s") + ":\n" + processors[0] + "\n");*/
        
        
        // Writes header to report
        report.println("Report file: " + args[0] + ".rpt");
        //report.println(numJobs + " Job" + ((numJobs==1) ? "":"s") + ":\n" + processors[0]); // Fixes plural mistakes
        report.println(numJobs + " Jobs:\n" + processors[0] + "\n");
        report.println("\n***********************************************************");
        /*System.out.println("Report file: " + args[0] + ".rpt");
        System.out.println(numJobs + " Job" + ((numJobs==1) ? "":"s") + ":\n" + processors[0]);
        System.out.println("\n***********************************************************");*/
        
        int time, jobsCompleted, totalWait, maxWait, temp;
        double averageWait;
        
        // Loops through required amount of processors
        for (int i = 1; i < numJobs; i++) {
            temp = 0;
            time = 0;
            jobsCompleted = 0;
            totalWait = 0;
            maxWait = 0;
            averageWait= 0;
            
            // Outputs processor header
            if (i != 1) trace.println();
            trace.println("*****************************");
            trace.println(i + " processor" + ((i==1) ? "":"s") + ":");
            trace.println("*****************************");
            /*System.out.println("*****************************");
            System.out.println(i + " processor" + ((i==1) ? "":"s") + ":");
            System.out.println("*****************************");*/
            
            // Outputs report header
            report.print(i + " processor" + ((i==1) ? "":"s") + ": ");
            //System.out.print(i + " processor" + ((i==1) ? "":"s") + ": ");
            
            // Outputs time = 0 case
            trace.println("time=" + time);
            trace.println(processorsOut(processors, i));
            /*System.out.println("time=" + time);
            System.out.println(processorsOut(processors, i));*/
            
            // Simulates until jobs processed
            while (jobsCompleted != numJobs) {
                time = getNextTime(processors, i);
                trace.println("time=" + time);
                //System.out.println("time=" + time);
                
                // Checks each processor for finishing jobs
                for (int j = 1; j <= i; j++) {
                    while (!(processors[j].isEmpty()) && time == ((Job) processors[j].peek()).getFinish()) {
                        processors[0].enqueue(processors[j].dequeue());
                        jobsCompleted++;
                    }
                }
                
                // Moves all arrived jobs to processors
                while (!(processors[0].isEmpty()) && time == ((Job) processors[0].peek()).getArrival()) {
                    processors[getShortest(processors, i)].enqueue(processors[0].dequeue());
                }
                
                // Computes finish time if needed
                for (int j = 1; j <= i; j++) {
                    if (!processors[j].isEmpty() && ((Job) processors[j].peek()).getFinish() == UNDEF) {
                        ((Job) processors[j].peek()).computeFinishTime(time);
                        temp = ((Job) processors[j].peek()).getWaitTime();
                        totalWait += temp;
                        if (temp > maxWait) maxWait = temp;
                    }
                }
               
                // Prints each required processor
                trace.println(processorsOut(processors, i));
                //System.out.println(processorsOut(processors, i));
            }
            
            averageWait = (double) totalWait/numJobs;
            
            report.printf("totalWait=%d, maxWait=%d, averageWait=%.2f\n", totalWait, maxWait, averageWait);
            //System.out.printf("totalWait=%d, maxWait=%d, averageWait=%.2f\n", totalWait, maxWait, averageWait);
            
            
            resetProcessors(processors);
            resetStorage(backup, processors[0]);
        }
        trace.close();
        report.close();
    }
}
