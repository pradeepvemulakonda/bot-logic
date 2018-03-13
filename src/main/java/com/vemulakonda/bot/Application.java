package com.vemulakonda.bot;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.ListWorkspacesOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.WorkspaceCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class Application {

    @RequestMapping("/message")
    @ResponseBody
    String chat() {
        Conversation service = new Conversation("2018-02-16");
        service.setUsernameAndPassword("4f70690b-f5b5-4bce-874d-2480678b4e4b", "7Qc3Ycxodzdc");
        ListWorkspacesOptions options = new ListWorkspacesOptions.Builder().build();

        WorkspaceCollection workspaces = service.listWorkspaces(options).execute();

        return workspaces.getWorkspaces().get(0).getName();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}