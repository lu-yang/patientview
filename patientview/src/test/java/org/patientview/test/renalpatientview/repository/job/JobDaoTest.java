package org.patientview.test.renalpatientview.repository.job;

import org.patientview.test.renalpatientview.helpers.RepositoryHelpers;
import org.patientview.renalpatientview.patientview.model.Conversation;
import org.patientview.renalpatientview.patientview.model.Job;
import org.patientview.renalpatientview.patientview.model.Message;
import org.patientview.renalpatientview.patientview.model.Specialty;
import org.patientview.renalpatientview.patientview.model.User;
import org.patientview.renalpatientview.patientview.model.enums.SendEmailEnum;
import org.patientview.test.renalpatientview.repository.BaseDaoTest;
import org.junit.Test;
import org.patientview.renalpatientview.repository.job.JobDao;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JobDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private JobDao jobDao;

    @Test
    public void testAddGetJob() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        Message message = repositoryHelpers.createMessage(conversation, user1, user2, "This is a message", true);
        Specialty specialty = repositoryHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job1 = new Job();
        job1.setCreator(user1);
        job1.setMessage(message);
        job1.setSpecialty(specialty);
        job1.setStatus(SendEmailEnum.PENDING);
        jobDao.save(job1);

        Job job2 = new Job();
        job2.setCreator(user2);
        job2.setMessage(message);
        job2.setSpecialty(specialty);
        job2.setStatus(SendEmailEnum.FAILED);
        jobDao.save(job2);


        assertTrue("Invalid id for Job 1 ", job1.getId() > 0);
        assertTrue("Invalid id for Job 2 ", job2.getId() > 0);

        List<Job> checkJobList = jobDao.getJobList(SendEmailEnum.PENDING);

        assertNotNull(checkJobList);
        assertEquals("Wrong number of job size", checkJobList.size(), 1);
        assertFalse("Job 2 found in SendEmailEnum PENDING", checkJobList.contains(job2));
        assertTrue("Job 1 not found in SendEmailEnum PENDING", checkJobList.contains(job1));
        assertEquals("Specialty not stored", checkJobList.get(0).getSpecialty(), specialty);
        assertEquals("Message not stored", checkJobList.get(0).getMessage(), message);
        assertEquals("User not stored", checkJobList.get(0).getCreator(), user1);
    }

}
