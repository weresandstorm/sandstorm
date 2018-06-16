package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import io.sandstorm.controller.domain.resource.QTestScriptDiscard;
import io.sandstorm.controller.domain.resource.TestScriptDiscard;
import io.sandstorm.controller.domain.resource.TestScriptDiscardRepo;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.springframework.stereotype.Repository;

@Repository
public class MgoTestScriptDiscardRepo extends BasicMgoRepo<TestScriptDiscard> implements TestScriptDiscardRepo {

    @Override
    protected EntityPath<TestScriptDiscard> serveEntityPath() {
        return QTestScriptDiscard.testScriptDiscard;
    }
}
