package io.github.ganzes.lang;

import java.util.List;
import java.util.stream.Collectors;

public class LangService {
    private LangRepository repository;

    public LangService(){
        this(new LangRepository());
    };

    public LangService(LangRepository repository){
        this.repository = repository;
    }

    public List<LangDTO> findAll (){
        List<Lang> findAllList = repository.findAll();
        return findAllList.stream()
        .map(LangDTO::new)
        .collect(Collectors.toList());
    }
}
