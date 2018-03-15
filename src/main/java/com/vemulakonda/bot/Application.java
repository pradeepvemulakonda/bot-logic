package com.vemulakonda.bot;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Controller
@SpringBootApplication
public class Application {

    @Value("${UN}")
    private String userName;

    @Value("${PASSWORD}")
    private String pwd;

    @Value("${WID}")
    private String workspaceId;

    @Value("${TOKEN}")
    private String token;

    @RequestMapping("/message")
    @ResponseBody
    @PostMapping
    String chat(@RequestBody InputText inputData) {
        System.out.println("====}" + token + "123455 " + inputData.getToken());
        if(!token.equalsIgnoreCase(inputData.getToken())) {
            throw new IllegalStateException();
        }

        System.out.println("----->>>>u:" + userName);
        System.out.println("----->>>>p:" + pwd);
        System.out.println("----->>>>w:" + workspaceId);

        Conversation service = new Conversation("2018-02-16");
        service.setUsernameAndPassword(userName, pwd);

        InputData input = new InputData.Builder(inputData.getMessage()).build();

        MessageOptions options = new MessageOptions.Builder(workspaceId)
                .input(input)
                .build();

        MessageResponse response = service.message(options).execute();

        return response.getOutput().getText().get(0);
    }

    @ExceptionHandler
    ResponseEntity handle(Exception e) {
        e.printStackTrace();
        System.out.println("----->>>>u:" + e);
        return new ResponseEntity("Opps, I need to go to the loo. Be right back.", HttpStatus.BAD_REQUEST);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
