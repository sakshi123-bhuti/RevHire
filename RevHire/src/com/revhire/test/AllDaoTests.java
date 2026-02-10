package com.revhire.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    UserDaoImplTest.class,
    JobDaoImplTest.class,
    ApplicationDaoImplTest.class,
    EmployerDaoImplTest.class,
    JobSeekerDaoImplTest.class,
    ResumeDaoImplTest.class,
    EducationDaoImplTest.class,
    ExperienceDaoImplTest.class,
    CertificationDaoImplTest.class,
    ProjectDaoImplTest.class,
    CompanyDaoImplTest.class,
    NotificationDaoImplTest.class
})
public class AllDaoTests {
    // This class remains empty. It is used only as a holder for the annotations above.
}
