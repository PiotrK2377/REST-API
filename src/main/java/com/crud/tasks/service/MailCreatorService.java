package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private CompanyDetails companyDetails;

    @Autowired
    private TaskRepository taskRepository;


    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("task_url","https://piotrk2377.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_name", companyDetails.getCompanyName());
        context.setVariable("company_goal", companyDetails.getCompanyGoal());
        context.setVariable("company_email", companyDetails.getCompanyEmail());
        context.setVariable("company_phone", companyDetails.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledTrelloMail(String message) {

        List<String> taskList = taskRepository.findAll().stream()
                .map(task -> task.getTitle())
                .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("task_url","https://piotrk2377.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_name", companyDetails.getCompanyName());
        context.setVariable("company_goal", companyDetails.getCompanyGoal());
        context.setVariable("company_email", companyDetails.getCompanyEmail());
        context.setVariable("company_phone", companyDetails.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("task_list", taskList);
        context.setVariable("goodbye_message","Until next time :)");
        return templateEngine.process("mail/created-scheduled-task-list-mail", context);
    }
}
