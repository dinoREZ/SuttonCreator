package org.example.dataModels;

import org.example.Query;
import org.example.Resource;

public interface IVisitor {

    void enterMetaModel(MetaModel metaModel);

    void exitMetaModel(MetaModel metaModel);

    void enterResource(Resource resource);

    void exitResource(Resource resource);

    void enterQuery(Query query);

    void exitQuery(Query query);

    void enterSubResource(Resource resource);

    void exitSubResource(Resource resource);

    void enterSubQuery(Query query);

    void exitSubQuery(Query query);
}
