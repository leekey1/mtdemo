package cn.com.hyun.framework.job;

import cn.com.hyun.framework.job.component.JobComponent;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by hyunwoo
 */
public abstract class SimpleJob extends JobComponent {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        executeJob(context.getMergedJobDataMap());
    }
}
