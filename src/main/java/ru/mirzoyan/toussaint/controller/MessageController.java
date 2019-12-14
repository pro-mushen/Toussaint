package ru.mirzoyan.toussaint.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirzoyan.toussaint.domain.Message;
import ru.mirzoyan.toussaint.domain.Views;
import ru.mirzoyan.toussaint.repo.MessageRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageRepo messagesRepo;

    @Autowired
    public MessageController(MessageRepo messagesRepo) {
        this.messagesRepo = messagesRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messagesRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.IdName.class)
    public Message getMessageById(
            @PathVariable("id") Message message
    ){
        return message;
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messagesRepo.save(message);
    }

    @PutMapping("{id}")
    public Message updateMessage(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ){
        BeanUtils.copyProperties(message,messageFromDb,"id");
        return messagesRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable Long id){
        messagesRepo.deleteById(id);
    }

}
