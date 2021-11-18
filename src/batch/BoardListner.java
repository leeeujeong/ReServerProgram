package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


@WebListener
public class BoardListner implements ServletContextListener {

	private Scheduler scheduler;
	
    public BoardListner() {
    	try {
			scheduler  = new StdSchedulerFactory().getScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
			if(scheduler != null) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
    		JobDetail job = JobBuilder.newJob(SelectBoardMaxHitJob.class)
    				.withIdentity("job", "group")
    				.build();
    		
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("trigger", "group")
    				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
    				.build();
    		
    		scheduler.scheduleJob(job, trigger);

    		scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 }
	

