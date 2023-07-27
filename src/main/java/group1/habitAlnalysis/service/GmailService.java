package group1.habitAlnalysis.service;

import group1.habitAlnalysis.entity.GmailEntity;
import group1.habitAlnalysis.model.GmailModel;
import group1.habitAlnalysis.repository.GmailRepository;
import org.springframework.stereotype.Service;

@Service
public class GmailService {
    private GmailRepository gmailRepository;

    public GmailService(GmailRepository gmailRepository) {
        this.gmailRepository = gmailRepository;
    }

    public String getGmailEntity(GmailEntity gmailEntity) {
        GmailEntity gmail = this.gmailRepository.findByEmail(gmailEntity.getEmail());
        GmailModel result = new GmailModel();
        if(gmail == null){
        GmailEntity gm = new GmailEntity();
        gm.setName(gmailEntity.getName());
        gm.setEmail(gmailEntity.getEmail());
        this.gmailRepository.save(gm);

        result.setName(gmailEntity.getName());
        result.setEmail(gmailEntity.getEmail());

        return "Okay";
        }else {
            return "Not Found";
        }
    }
}
