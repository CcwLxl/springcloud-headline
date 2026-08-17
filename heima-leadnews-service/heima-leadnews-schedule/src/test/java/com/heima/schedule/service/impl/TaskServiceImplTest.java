package com.heima.schedule.service.impl;

import com.heima.common.constants.ScheduleConstants;
import com.heima.model.schedule.dtos.Task;
import com.heima.schedule.ScheduleApplication;
import com.heima.schedule.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;


@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void addTask(){
        Task task = new Task();
        task.setTaskType(300);
        task.setPriority(60);
        task.setParameters("task test".getBytes());
        task.setExecuteTime(new Date().getTime()+5000);
        System.out.println("test裏打印task: " + task.toString());

        long taskId = taskService.addTask(task);
        System.out.println(taskId);

    }

    @Test
    public void cancelTask(){
        taskService.cancelTask(2086734982373965825L);
    }

    @Test
    public void testPoll(){
        Task task = taskService.poll(100, 50);
        System.out.println(task);
    }


    @Test
    public void addTask11(){
        for (int i=0;i<5;i++){
            Task task = new Task();
            task.setTaskType(100+i);
            task.setPriority(50);
            task.setParameters("task test".getBytes());
            task.setExecuteTime(new Date().getTime()+500*i);

            long taskId = taskService.addTask(task);
            System.out.println(taskId);
        }

    }



}