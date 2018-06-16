package io.sandstorm.controller.app;

import io.sandstorm.common.ViolateBizConstraintException;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.LoadInjectorRepo;
import io.sandstorm.controller.domain.resource.LoadInjectorService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoadInjectorApp {

    @Resource
    private LoadInjectorRepo loadInjectorRepo;
    @Resource
    private LoadInjectorService loadInjectorService;

    public void create(LoadInjector loadInjector) {
        loadInjectorService.register(loadInjector);
    }

    public LoadInjector detail(ObjectId id) {
        return loadInjectorRepo.get(id);
    }

    // TODO: 17/01/2018 暂时不提供 LoadInjector 的删除操作，
    // 因为 JobExecution、JobSliceExecution 都引用了 LoadInjector，
    // 如果删除了被引用的 LoadInjector，从仓库中获取 JobExecution、JobSliceExecution 时就会报错。
    // 后续迭代可考虑在 JobExecution、JobSliceExecution 中内嵌 LoadInjector 来解决此问题。
    public void delete(ObjectId id) {
        LoadInjector loadInjector = loadInjectorRepo.get(id);
        if (loadInjector.isUsable()) {
            loadInjectorRepo.delete(id);
        } else {
            throw new ViolateBizConstraintException(
                    String.format("%s with id '%s' is in use, so can't be deleted",
                            LoadInjector.class.getSimpleName(), id));
        }
    }

}
