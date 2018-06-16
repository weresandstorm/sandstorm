package io.sandstorm.controller.portadapter.persist.mgo;

//
//import com.google.common.collect.Lists;
//import com.mongodb.MongoClient;
//import com.querydsl.core.types.EntityPath;
//import com.querydsl.core.types.Predicate;
//import io.sandstorm.common.domain.repo.Repo;
//import io.sandstorm.controller.portadapter.persist.EnumStatus;
//import org.bson.types.ObjectId;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.Key;
//import org.mongodb.morphia.Morphia;
//import org.mongodb.morphia.query.Query;
//import org.mongodb.morphia.query.UpdateOperations;
//import org.mongodb.morphia.query.UpdateResults;
//
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(JUnit4.class)
//public class MgoRepoTest {
//
//    private static MongoClient mongo;
//    private static Morphia morphia;
//    private static Datastore datastore;
//
//    private static UserRepo userRepo;
//    private static JobRepo jobRepo;
//
//    @BeforeClass
//    public static void setUpClass() {
//        /*EntityIdCodec entityIdCodec = new EntityIdCodec();
//        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
//                MongoClient.getDefaultCodecRegistry(),
//                CodecRegistries.fromCodecs(entityIdCodec)
//        );
//        MongoClientOptions mgoOptions = MongoClientOptions
//                .builder()
//                .codecRegistry(codecRegistry)
//                .build();
//        mongo = new MongoClient("localhost", mgoOptions);*/
//        mongo = new MongoClient();
//        morphia = new Morphia().map(User.class);
//        //morphia.getMapper().getConverters().addConverter(new EntityIdConverter());
//        List<PojoMapper> pojoMappers = Lists.newArrayList(new Job.StatusMapper());
//        CustomObjectFactory objectFactory = new CustomObjectFactory();
//        objectFactory.setPojoMappers(pojoMappers);
//        morphia.getMapper().getOptions().setObjectFactory(objectFactory);
//        datastore = morphia.createDatastore(mongo, "sandstorm-test");
//
//        UserRepo userRepo = new UserRepo();
//        userRepo.setMorphia(morphia);
//        userRepo.setDatastore(datastore);
//        MgoRepoTest.userRepo = userRepo;
//
//        JobRepo jobRepo = new JobRepo();
//        jobRepo.setMorphia(morphia);
//        jobRepo.setDatastore(datastore);
//        MgoRepoTest.jobRepo = jobRepo;
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//        mongo.close();
//    }
//
//    @Before
//    public void setUp() {
//        // do nothing
//    }
//
//    @After
//    public void tearDown() {
//        datastore.delete(userRepo.newMorphiaQuery());
//        datastore.delete(jobRepo.newMorphiaQuery());
//    }
//
//    @Test
//    public void saveNew() {
//        User user = new User("Jim", "Green", 20, new Date());
//        User returnedUser = userRepo.save(user);
//
//        User userInStore = datastore.get(User.class, returnedUser.id());
//        assertNotNull(userInStore);
//        assertEquals(returnedUser, userInStore);
//    }
//
//    @Test
//    public void saveExisting() {
//        User user = new User("Jim", "Green", 20, new Date());
//        User returnedUser = userRepo.save(user);
//
//        User friend = userRepo.save(new User("Carrie", "Johnson"));
//        returnedUser.modify("Lily", "Simon", friend);
//        userRepo.save(returnedUser);
//
//        User userInStore = datastore.get(User.class, returnedUser.id());
//        assertNotNull(userInStore);
//        assertEquals(returnedUser, userInStore);
//    }
//
//    @Test
//    public void get() {
//        User friend = userRepo.save(new User("Carrie", "Johnson"));
//        User user = new User("Lily", "Simon", friend);
//        User returnedUser = userRepo.save(user);
//
//        User userInStore = userRepo.get(returnedUser.id());
//        assertEquals(returnedUser, userInStore);
//    }
//
//    @Test
//    public void find() {
//        Key<User> key1 = datastore.save(new User("Lily", "Simon"));
//        Key<User> key2 = datastore.save(new User("Jim", "Green"));
//        Key<User> key3 = datastore.save(new User("Snow", "John"));
//
//        QUser qUser = new QUser("users");
//        Predicate criteria = qUser.id.in(
//                (ObjectId) key1.getId(),
//                (ObjectId) key2.getId(),
//                (ObjectId) key3.getId());
//        List<User> users = userRepo.find(criteria);
//        assertEquals(3, users.size());
//    }
//
//    @Test
//    public void findAll() {
//        Key<User> key1 = datastore.save(new User("Lily", "Simon"));
//        Key<User> key2 = datastore.save(new User("Jim", "Green"));
//        Key<User> key3 = datastore.save(new User("Snow", "John"));
//
//        QUser qUser = new QUser("users");
//    }
//
//    @Test
//    public void delete() {
//        datastore.save(new User("Lily", "Shawn", User.Gender.FEMALE));
//        datastore.save(new User("Jim", "Green", User.Gender.MALE));
//        datastore.save(new User("Snow", "John", User.Gender.MALE));
//
//        QUser qUser = QUser.user;
//        int affectedCount = userRepo.delete(qUser.gender.eq(User.Gender.MALE));
//        assertEquals(2, affectedCount);
//
//        List<User> theUsers = userRepo.newMorphiaQuery()
//                .field("gender").equal(User.Gender.MALE)
//                .asList();
//        assertEquals(0, theUsers.size());
//    }
//
//    @Test
//    public void saveComplexEnum() {
//        Job job = new Job();
//        job.name = "FindBestPrice";
//        job.status = Status.preparing;
//        Job theJob = jobRepo.save(job);
//        assertEquals(job.id(), theJob.id());
//    }
//
//    @Test
//    public void findByComplexEnum() {
//        Job job = new Job();
//        job.name = "FindBestPrice";
//        job.status = Status.preparing;
//        jobRepo.save(job);
//
//        List<Job> jobs = jobRepo.findByStatus(Status.preparing);
//        assertEquals(1, jobs.size());
//        assertTrue(jobs.get(0).status == Status.preparing);
//    }
//
//    @Test
//    public void findByEnumStatus() {
//        Job job = new Job();
//        job.name = "FindBestPrice";
//        job.enumStatus = EnumStatus.preparing;
//        jobRepo.save(job);
//
//        List<Job> jobs = jobRepo.findNotEnded();
//        assertEquals(1, jobs.size());
//        assertTrue(jobs.get(0).enumStatus == EnumStatus.preparing);
//    }
//
//    @Test
//    public void findByUser() {
//        User user = new User("lily", "qin");
//        userRepo.save(user);
//
//        Job job = new Job();
//        job.name = "FindBestPrice";
//        job.enumStatus = EnumStatus.preparing;
//        job.user = user;
//        jobRepo.save(job);
//
//        List<Job> jobs = jobRepo.findByUser(user);
//        assertEquals(1, jobs.size());
//        assertTrue(jobs.get(0).enumStatus == EnumStatus.preparing);
//    }
//
//    @Test
//    public void updateByComplexEnum1() {
//        Job job = new Job();
//        job.name = "job1";
//        job.status = Status.preparing;
//        jobRepo.save(job);
//
//        job.name = "job2";
//        job.status = Status.prepared;
//        int affected = jobRepo.updateByStatus(job);
//        assertEquals(1, affected);
//
//        Job revisedJob = jobRepo.get(job.id());
//        assertEquals("job2", revisedJob.name);
//        assertEquals(Status.prepared, revisedJob.status);
//    }
//
//    @Test
//    public void updateByComplexEnum2() {
//        Job job = new Job();
//        job.name = "job1";
//        job.status = Status.preparing;
//        jobRepo.save(job);
//
//        job.name = "job3";
//        int affected = jobRepo.updateByStatus(job);
//        assertEquals(1, affected);
//
//        Job revisedJob = jobRepo.get(job.id());
//        assertEquals("job3", revisedJob.name);
//        assertEquals(Status.preparing, revisedJob.status);
//    }
//
//    @Test
//    public void updateByComplexEnum3() {
//        Job job = new Job();
//        job.name = "job1";
//        job.status = Status.running;
//        jobRepo.save(job);
//
//        job.name = "job4";
//        job.status = Status.prepared;
//        int affected = jobRepo.updateByStatus(job);
//        assertEquals(0, affected);
//
//        Job revisedJob = jobRepo.get(job.id());
//        assertEquals("job1", revisedJob.name);
//        assertEquals(Status.running, revisedJob.status);
//    }
//
//    @Test
//    public void updateByComplexEnum4() {
//        Job job = new Job();
//        job.name = "job1";
//        job.status = Status.failed;
//        jobRepo.save(job);
//
//        job.name = "job5";
//        job.status = Status.stopping;
//        int affected = jobRepo.updateByStatus(job);
//        assertEquals(0, affected);
//
//        Job revisedJob = jobRepo.get(job.id());
//        assertEquals("job1", revisedJob.name);
//        assertEquals(Status.failed, revisedJob.status);
//    }
//
//    private static class UserRepo extends BasicMgoRepo<User> implements Repo<User> {
//        @Override
//        protected EntityPath<User> serveEntityPath() {
//            return null;
//        }
//    }
//
//    private static class JobRepo extends BasicMgoRepo<Job> implements Repo<Job> {
//
//        List<Job> findByStatus(Status status) {
//            return newMorphiaQuery().field("status").equal(status).asList();
//        }
//
//        List<Job> findNotEnded() {
//            return newMorphiaQuery().field("enumStatus")
//                    .notIn(EnumStatus.endedStatuses())
//                    .asList();
//        }
//
//        List<Job> findByUser(User user) {
//            Query<Job> query = newMorphiaQuery();
//            query.and(
//                    query.criteria("user").equal(user),
//                    query.criteria("enumStatus").notIn(EnumStatus.endedStatuses())
//            );
//            return query.asList();
//        }
//
//        int updateByStatus(Job revision) {
//            UpdateOperations<Job> updateOps = newUpdateOps()
//                    .set("name", revision.name)
//                    .set("status", revision.status);
//            Query<Job> query = newMorphiaQuery();
//            query.or(
//                    query.criteria("status").equal(revision.status),
//                    query.criteria("status.level").lessThan(revision.status.level())
//            );
//            UpdateResults result = datastore.update(query, updateOps);
//            return result.getUpdatedCount();
//        }
//
//        @Override
//        protected EntityPath<Job> serveEntityPath() {
//            return QJob.job;
//        }
//    }
//
//}