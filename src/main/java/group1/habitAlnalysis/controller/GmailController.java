package group1.habitAlnalysis.controller;

import group1.habitAlnalysis.entity.GmailEntity;
import group1.habitAlnalysis.model.GmailModel;
import group1.habitAlnalysis.service.GmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GmailController {


    private GmailService gmailService;

    public GmailController(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @PostMapping("/api/gmail")
    public String getGmailEntity(@RequestBody GmailEntity gmailEntity){
        return this.gmailService.getGmailEntity(gmailEntity);
    }
}
