package ru.mirzoyan.toussaint.controller;

import org.springframework.web.bind.annotation.*;
import ru.mirzoyan.toussaint.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("message")
public class MainController {

    private AtomicLong counter = new AtomicLong(4);
    private List<Map<String, String>> messages= new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>(){{ put("id", "1"); put("text", "first message");}});
        add(new HashMap<String, String>(){{ put("id", "2"); put("text", "second message");}});
        add(new HashMap<String, String>(){{ put("id", "3"); put("text", "third message"); put("del me", "WTF");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessageById(
            @PathVariable("id") String idMessage
    ){
        return getMessage(idMessage);
    }

    private Map<String, String> getMessage(String idMessage) {
        return messages.stream()
                .filter(message->idMessage.equals(message.get("id")))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> addMessage(@RequestBody Map<String, String> message){
        message.put("id", String.valueOf(counter.getAndAdd(1)));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(
            @PathVariable String id,
            @RequestBody Map<String, String> message){
        Map<String, String> messageFromDb = getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id",id);
        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }

}
