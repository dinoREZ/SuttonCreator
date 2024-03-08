import org.example.*;
import org.example.dataModels.DataModel;
import org.example.dataModels.MetaModel;
import org.example.dataModels.Visitor;
import org.glassfish.jersey.linking.InjectLink;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MetaModelToDataModelsTest {
    static MetaModel metaModel;

    @BeforeAll
    static void createMetaModel() {
        metaModel = new MetaModel();
        metaModel.setBasePackage("de.fhws.fiw.fds.implementation");
        metaModel.addResource(new Resource()
                .setName("Student")
                .addAttribute("String" , "firstName")
                .addAttribute("String", "lastName")
                .addQuery(new Query()
                        .addAttribute("String", "firstName", "")
                        .addAttribute("String", "lastName", "")
                )
                .addLink("selfLink", Link.SelfLinkOnPrimary("students"))
                .addLink("courses", new Link(InjectLink.Style.ABSOLUTE, "students/${instance.id}/courses", "getCoursesOfStudents", "courses", "true"))
                .addSubResource(new Resource()
                        .setName("Course")
                        .addAttribute("String", "name")
                        .addAttribute("String", "room")
                        .addQuery(new Query()
                                .addAttribute("String", "name", "")
                                .addAttribute("String", "room", "")
                        )
                        .addLink("selfLinkOnSecond", Link.SelfLinkOnSecondary("students", "courses"))
                        .addLink("selfLink", Link.SelfLinkOnPrimary("courses"))
                )
        );
    }

    @Test
    void compareSingleMethodAndVisitorApproach() {
        List<DataModel> singleMethodApproach = DataManager.getAllDataModels(metaModel);
        Visitor visitor = new Visitor();
        metaModel.accept(visitor);
        List<DataModel> visitorApproach = visitor.getDataModels();

        assertEquals(singleMethodApproach.size(), visitorApproach.size());

        assertTrue(singleMethodApproach.containsAll(visitorApproach));
    }

    @Test
    void compareSingleMethodAndMultiMethodApproach() {
        List<DataModel> singleMethodApproach = DataManager.getAllDataModels(metaModel);
        List<DataModel> multiMethodApproach = new ArrayList<>();
        multiMethodApproach.addAll(DataManager.getAllDaoFactories(metaModel));
        multiMethodApproach.addAll(DataManager.getAllModels(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDaos(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDaoImpls(metaModel));
        multiMethodApproach.addAll(DataManager.getAllModelDBs(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDaoHibernates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDaoHibernateImpls(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDaoAdapters(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDeleteByIdOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllPersistOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllReadAllOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllReadByIdOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllUpdateOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllQueryOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDaos(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoImpls(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDBs(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoHibernate(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoHibernateImpl(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDaoAdapter(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDeleteByIdOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDeleteByPrimaryIdOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationDeleteBySecondaryIdOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationPersistOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationReadAllOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationReadByIdOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationUpdateOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationQueryOperations(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDeleteStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllGetCollectionStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllGetStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllPostStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllPutStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelTypes(metaModel));
        multiMethodApproach.addAll(DataManager.getAllUris(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDeleteRelationStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllGetRelationCollectionStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllGetRelationStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllPostRelationStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllPutRelationStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationRelTypes(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationUris(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDispatcherStates(metaModel));
        multiMethodApproach.addAll(DataManager.getAllServices(metaModel));
        multiMethodApproach.addAll(DataManager.getAllDispatcherServices(metaModel));
        multiMethodApproach.addAll(DataManager.getAllQueries(metaModel));
        multiMethodApproach.addAll(DataManager.getAllRelationQueries(metaModel));
        multiMethodApproach.addAll(DataManager.getAllStarts(metaModel));
        multiMethodApproach.addAll(DataManager.getAllApplications(metaModel));

        assertEquals(singleMethodApproach.size(), multiMethodApproach.size());

        assertTrue(singleMethodApproach.containsAll(multiMethodApproach));
    }
}
