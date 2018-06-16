package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import io.sandstorm.controller.domain.resource.QTestScript;
import io.sandstorm.controller.domain.resource.TestScript;
import io.sandstorm.controller.domain.resource.TestScriptRepo;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.springframework.stereotype.Repository;

@Repository
public class MgoTestScriptRepo extends BasicMgoRepo<TestScript> implements TestScriptRepo {

    @Override
    public boolean exist(String jarAlias) {
        QTestScript q = QTestScript.testScript;
        return exist(q.jarAlias.eq(jarAlias));
    }

    @Override
    protected EntityPath<TestScript> serveEntityPath() {
        return QTestScript.testScript;
    }
}
