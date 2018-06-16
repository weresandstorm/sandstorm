package io.sandstorm.controller.domain.resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class LoadInjectorService {

    @Resource
    private LoadInjectorRepo loadInjectorRepo;

    public void register(LoadInjector loadInjector) {
        Optional<LoadInjector> theOne = loadInjectorRepo.getOptional(loadInjector.hostname());
        LoadInjector injectorToSave;
        if (theOne.isPresent()) {
            injectorToSave = theOne.get();
            injectorToSave.mergeFrom(loadInjector);
        } else {
            injectorToSave = loadInjector;
            loadInjector.beginDomainLifeCycle();
        }
        loadInjectorRepo.save(injectorToSave);
    }

    public void handleEventOnInjector(ObjectId injectorId, Consumer<LoadInjector> eventHandling) {
        LoadInjector loadInjector = loadInjectorRepo.get(injectorId);
        eventHandling.accept(loadInjector);
        loadInjectorRepo.save(loadInjector);
    }

}