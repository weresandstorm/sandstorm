package io.sandstorm.controller.portadapter.schedule.spring;

import io.sandstorm.controller.portadapter.schedule.TestJobExecuteListener;
import io.sandstorm.controller.portadapter.schedule.TestJobTriggerListener;
import javax.annotation.Resource;

import io.sandstorm.controller.portadapter.schedule.TestJobExecuteListener;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzSchedulerConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private QuartzSpringJobFactory springJobFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setBeanName("TestJobSchedulerFactoryBean");
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactoryBean.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(springJobFactory);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactoryBean.setGlobalJobListeners(new TestJobExecuteListener());
        schedulerFactoryBean.setGlobalTriggerListeners(new TestJobTriggerListener());
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
