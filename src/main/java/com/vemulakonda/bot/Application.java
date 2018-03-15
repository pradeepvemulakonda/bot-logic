package com.vemulakonda.bot;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Value;

@Controller
@SpringBootApplication
public class Application {

    @Value("${UN}")
    private String userName;

    @Value("${PASSWORD}")
    private String pwd;

    @Value("${WID}")
    private String workspaceId;

    @RequestMapping("/message")
    @ResponseBody
    @PostMapping
    String chat(@RequestBody String inputData) {
        System.out.println("----->>>>u:" + userName);
        System.out.println("----->>>>p:" + pwd);
        System.out.println("----->>>>w:" + workspaceId);

        Conversation service = new Conversation("2018-02-16");
        service.setUsernameAndPassword(userName, pwd);

        InputData input = new InputData.Builder(inputData).build();

        MessageOptions options = new MessageOptions.Builder(workspaceId)
                .input(input)
                .build();

        MessageResponse response = service.message(options).execute();

        return response.getOutput().getText().get(0);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}