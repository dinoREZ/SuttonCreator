import org.example.DataManager;
import org.example.dataModels.DataModel;
import org.example.dataModels.Visitor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.Config.META_MODEL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetaModelToDataModelsTest {

    @Test
    void compareSingleMethodAndVisitorApproach() {
        List<DataModel> singleMethodApproach = DataManager.getAllDataModels(META_MODEL);
        Visitor visitor = new Visitor();
        META_MODEL.accept(visitor);
        List<DataModel> visitorApproach = visitor.getDataModels();

        assertEquals(singleMethodApproach.size(), visitorApproach.size());

        assertTrue(singleMethodApproach.containsAll(visitorApproach));
    }

    @Test
    void compareSingleMethodAndMultiMethodApproach() {
        List<DataModel> singleMethodApproach = DataManager.getAllDataModels(META_MODEL);
        List<DataModel> multiMethodApproach = new ArrayList<>();
        multiMethodApproach.addAll(DataManager.getAllDaoFactories(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllModels(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDaos(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDaoImpls(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllModelDBs(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDaoHibernates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDaoHibernateImpls(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDaoAdapters(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDeleteByIdOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllPersistOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllReadAllOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllReadByIdOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllUpdateOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllQueryOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDaos(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoImpls(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDBs(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoHibernate(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoHibernateImpl(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoAdapter(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDeleteByIdOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDeleteByPrimaryIdOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationDeleteBySecondaryIdOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationPersistOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationReadAllOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationReadByIdOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationUpdateOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationQueryOperations(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDeleteStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllGetCollectionStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllGetStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllPostStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllPutStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelTypes(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllUris(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDeleteRelationStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllGetRelationCollectionStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllGetRelationStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllPostRelationStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllPutRelationStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationRelTypes(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationUris(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDispatcherStates(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllServices(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDispatcherServices(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllQueries(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationQueries(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllStarts(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllApplications(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllDatabaseInstallers(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllNoAuthNeededAuthenticationProviders(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllAnyApiKeyRateLimiters(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllReadAllQueries(META_MODEL));
        multiMethodApproach.addAll(DataManager.getAllRelationReadAllQueries(META_MODEL));

        assertEquals(singleMethodApproach.size(), multiMethodApproach.size());

        assertTrue(singleMethodApproach.containsAll(multiMethodApproach));
    }
}
