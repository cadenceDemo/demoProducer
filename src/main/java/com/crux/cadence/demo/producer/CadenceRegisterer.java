package com.crux.cadence.demo.producer;

import com.crux.cadence.demo.producer.activity.FetchWeatherActivityImpl;
import com.uber.cadence.DomainAlreadyExistsError;
import com.uber.cadence.EntityNotExistsError;
import com.uber.cadence.RegisterDomainRequest;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CadenceRegisterer implements ApplicationListener<ContextRefreshedEvent> {
    @NonNull
    private String taskList;
    @NonNull
    WorkflowClient workflowClient;
    @NonNull
    FetchWeatherActivityImpl fetchWeatherActivity;
    public CadenceRegisterer(@Value("${app.cadence.tasklist}") String taskList,
                             WorkflowClient workflowClient,
                             FetchWeatherActivityImpl fetchWeatherActivity) {
        this.taskList = taskList;
        this.workflowClient = workflowClient;
        this.fetchWeatherActivity = fetchWeatherActivity;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker(taskList);
        worker.registerActivitiesImplementations(fetchWeatherActivity);
        factory.start();
    }
}