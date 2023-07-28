package group1.habitAlnalysis.controller;

import group1.habitAlnalysis.entity.CateEntity;
import group1.habitAlnalysis.repository.CateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CateController {
    private CateRepository cateRepository;

    public CateController(CateRepository cateRepository) {
        this.cateRepository = cateRepository;
    }

    @GetMapping("/api/get/cate")
    public List<CateEntity> getCate(){
        return this.cateRepository.findAll();
    }
}
