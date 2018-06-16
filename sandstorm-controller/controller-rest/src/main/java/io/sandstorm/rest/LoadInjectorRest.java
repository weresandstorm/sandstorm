package io.sandstorm.rest;

import io.sandstorm.common.query.domain.Page;
import io.sandstorm.rest.validation.LoadInjectorValidator;
import io.sandstorm.controller.app.FindService;
import io.sandstorm.controller.app.LoadInjectorApp;
import io.sandstorm.controller.app.LoadInjectorQParams;
import io.sandstorm.controller.domain.resource.LoadInjector;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static io.sandstorm.common.InputAssert.notNull;

@RestController
@RequestMapping("/load-injectors")
public class LoadInjectorRest {

    @Resource
    private LoadInjectorApp loadInjectorApp;
    @Resource
    private FindService findService;

    @PostMapping
    public void create(@RequestBody LoadInjector loadInjector) {
        notNull(loadInjector, "loadInjector from request body");
        loadInjector.accept(LoadInjectorValidator.object);
        loadInjectorApp.create(loadInjector);
    }

    @GetMapping
    public Page<LoadInjector> list(LoadInjectorQParams params) {
        notNull(params, "query params");
        return findService.findLoadInjectors(params);
    }
    
    @GetMapping("/{id}")
    public LoadInjector detail(@PathVariable("id") ObjectId id) {
        return loadInjectorApp.detail(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") ObjectId id) {
        loadInjectorApp.delete(id);
    }

}
