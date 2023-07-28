package group1.habitAlnalysis.service;

import group1.habitAlnalysis.entity.GmailEntity;
import group1.habitAlnalysis.model.GmailModel;
import group1.habitAlnalysis.model.ResponseModel;
import group1.habitAlnalysis.repository.GmailRepository;
import org.springframework.stereotype.Service;

@Service
public class GmailService {
    private final GmailRepository gmailRepository;

    public GmailService(GmailRepository gmailRepository) {
        this.gmailRepository = gmailRepository;
    }

    public ResponseModel getGmailEntity(GmailEntity gmailEntity) {
        GmailEntity gmail = this.gmailRepository.findByEmail(gmailEntity.getEmail());
        GmailModel result = new GmailModel();
        ResponseModel response = new ResponseModel();
        if(gmail == null){
        GmailEntity gm = new GmailEntity();
        gm.setName(gmailEntity.getName());
        gm.setEmail(gmailEntity.getEmail());
        this.gmailRepository.save(gm);

        response.setStatus(200);
        response.setMessage("Ok");
        }
        return response;
    }
}