package com.vemulakonda.bot;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class Application {

    @RequestMapping("/message")
    @ResponseBody
    @PostMapping
    String chat(@RequestBody String inputData) {
        Conversation service = new Conversation("2018-02-16");
        service.setUsernameAndPassword("4f70690b-f5b5-4bce-874d-2480678b4e4b", "7Qc3Ycxodzdc");

        String workspaceId = "ae478b9a-7a27-4cd7-83d6-cabdefc71393";

        InputData input = new InputData.Builder(inputData).build();

        MessageOptions options = new MessageOptions.Builder(workspaceId)
                .input(input)
                .build();

        MessageResponse response = service.message(options).execute();

        return response.getOutput().getText().get(0).toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}