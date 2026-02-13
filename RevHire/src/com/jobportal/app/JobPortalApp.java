package com.jobportal.app;
import com.jobportal.dao.*;
import com.jobportal.daoimpl.*;
import com.jobportal.dto.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;

public class JobPortalApp {
    private static final Logger logger = LogManager.getLogger(JobPortalApp.class);

    // DAOs - Implementations of your database logic
    private static final UserDao userDao = new UserDaoImpl();
    private static final JobSeekerDao seekerDao = new JobSeekerDaoImpl();
    private static final EmployerDao employerDao = new EmployerDaoImpl();
    private static final CompanyDao companyDao = new CompanyDaoImpl();
    private static final JobDao jobDao = new JobDaoImpl();
    private static final ApplicationDao appDao = new ApplicationDaoImpl();
    private static final NotificationDao notifDao = new NotificationDaoImpl();
    private static final ResumeDaoImpl resumeDao =new ResumeDaoImpl();
    private static User currentUser = null;
    private static Scanner sc = new Scanner(System.in);

    
    
    public static void main(String[] args) {
        logger.info("RevHire Application Started Successfully!");
        
        System.out.println("====================================================");
        System.out.println("   Welcome to RevHire: Your Career Starts Here      ");
        System.out.println("====================================================");
        
        while (true) {
            if (currentUser == null) {
                showMainMenu();
            } else if (currentUser.getRole().equalsIgnoreCase("JOB_SEEKER")) {
                showJobSeekerDashboard();
            } else if (currentUser.getRole().equalsIgnoreCase("EMPLOYER")) {
                showEmployerDashboard();
            }
        }
    }

    // --- MAIN MENU ---
    private static void showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Register as Job Seeker\n2. Register as Employer\n3. Login\n4. Forgot Password\n5. Exit");
        System.out.print("Select an option: ");
        
        try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> register("JOB_SEEKER");
                case 2 -> register("EMPLOYER");
                case 3 -> login();
                case 4 -> forgotPassword();
                case 5 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        } catch (Exception e) { System.out.println("Please enter a valid number."); }
    }

    // --- AUTHENTICATION & REGISTRATION ---
    private static void register(String role) {
        System.out.println("\n--- " + role + " Registration ---");
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        System.out.print("Security Question: "); String q = sc.nextLine();
        System.out.print("Answer: "); String a = sc.nextLine();

        User user = new User(email, pass, role);
        user.setSecurityQuestion(q);
        user.setSecurityAnswer(a);

        if (userDao.registerUser(user)) {
            User newlyCreatedUser = userDao.getUserByEmail(email);
            int userId = newlyCreatedUser.getId();

            if (role.equals("JOB_SEEKER")) {
                System.out.print("Full Name: "); String name = sc.nextLine();
                JobSeeker seeker = new JobSeeker();
                seeker.setUserId(userId);
                seeker.setFullName(name);
                seekerDao.createJobSeeker(seeker);
            } else {
                System.out.print("Contact Name: "); String name = sc.nextLine();
                Employer emp = new Employer();
                emp.setUserId(userId);
                emp.setContactName(name);
                employerDao.createEmployer(emp);
            }
            System.out.println("Registration Successful! Please login.");
        } else {
            System.out.println("Registration Failed. Email might exist.");
        }
    }

    private static void login() {
        System.out.print("Email: "); String e = sc.nextLine();
        System.out.print("Password: "); String p = sc.nextLine();
        currentUser = userDao.login(e, p);
        if (currentUser == null) System.out.println("Login Failed.");
    }

    private static void forgotPassword() {
        System.out.print("Enter Email: "); String email = sc.nextLine();
        User u = userDao.getUserByEmail(email);
        if (u != null) {
            System.out.println("Security Question: " + u.getSecurityQuestion());
            System.out.print("Your Answer: ");
            if (userDao.verifySecurityAnswer(email, u.getSecurityQuestion(), sc.nextLine())) {
                System.out.print("New Password: ");
                userDao.updatePassword(u.getId(), sc.nextLine());
                System.out.println("Password reset successful.");
            } else {
                System.out.println("Incorrect answer.");
            }
        }
    }

    // --- EMPLOYER FLOW ---
    private static void showEmployerDashboard() {
    	
        Employer emp = employerDao.getEmployerByUserId(currentUser.getId());
        if (emp == null) { logout(); return; }

        System.out.println("\n--- EMPLOYER DASHBOARD (Contact: " + emp.getContactName() + ") ---");
        System.out.println("1. Post Job\n2. My Jobs\n3. Manage Applications\n4. Company Profile\n5. Logout");
        System.out.print("Choice: ");
        
        try {
            int choice = Integer.parseInt(sc.nextLine());
            Company company = companyDao.getCompanyByEmployer(emp.getId());

            switch (choice) {
            case 1 -> {
      
            	if (company == null || company.getId() <= 0) {
            	    System.out.println("[!] Error: You must create a Company Profile (Option 4) before posting a job.");
            	} else {
            	    // This ensures the ID passed to postJob is a valid primary key from the companies table
            	    postJob(company.getId()); 
            	}
                // Re-fetch the company right before posting to ensure we have the LATEST ID
                Company latestCompany = companyDao.getCompanyByEmployer(emp.getId());
                
                if (latestCompany == null) {
                    System.out.println("[!] No company found. Please create one (Option 4).");
                } else {
                    // Double check: If this ID doesn't exist in the DB, it will fail.
                    postJob(latestCompany.getId()); 
                }
            }
              
                case 2 -> {
                    if (company == null) System.out.println("No company registered.");
                    else manageJobs(company.getId());
                }
                case 3 -> viewApplicants(emp.getId());
                case 4 -> manageCompany(emp.getId());
                case 5 -> logout();
            }
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
  

    private static void postJob(int companyId) {
    	 if (companyId <= 0) {
             System.out.println("Error: Invalid Company ID. Please recreate your profile.");
             return;
         }
         
        System.out.println("\n--- Post a New Job Opening ---");
        Job job = new Job();
        job.setCompanyId(companyId); 

        System.out.print("Job Title: "); job.setTitle(sc.nextLine());
        System.out.print("Description: "); job.setDescription(sc.nextLine());
        System.out.print("Location: "); job.setLocation(sc.nextLine());
        System.out.print("Job Type: "); job.setJobType(sc.nextLine());

        try {
            System.out.print("Salary: "); 
            job.setSalary(Double.parseDouble(sc.nextLine()));
        } catch (Exception e) { job.setSalary(0.0); }

        System.out.print("Experience Level (e.g., 2-4 years): "); 
        job.setExperience(sc.nextLine());

        job.setStatus("OPEN");
        job.setPostedDate(LocalDateTime.now());

        if (jobDao.postJob(job)) {
            System.out.println("Job posted successfully!");
        } else {
            System.out.println("Failed to post job. Please check database connectivity.");
        }
    }

    private static void manageCompany(int employerId) {
        Company company = companyDao.getCompanyByEmployer(employerId);
        if (company == null) {
            System.out.println("\n--- Create Company Profile ---");
            company = new Company();
            company.setEmployerId(employerId);
            System.out.print("Company Name: "); company.setName(sc.nextLine());
            System.out.print("Industry: "); company.setIndustry(sc.nextLine());
            System.out.print("Description: "); company.setDescription(sc.nextLine());
            System.out.print("Website: "); company.setWebsite(sc.nextLine());
            System.out.print("Location: "); company.setLocation(sc.nextLine());

            if (companyDao.createCompany(company)) {
                System.out.println("Company profile created successfully!");
            }
        } else {
            System.out.println("\n--- Company Profile Found ---");
            System.out.println("Name: " + company.getName());
            System.out.println("Location: " + company.getLocation());
        }
    }

    // --- JOB SEEKER FLOW ---
    private static void showJobSeekerDashboard() {
        JobSeeker seeker = seekerDao.getJobSeekerByUserId(currentUser.getId());
        if (seeker == null) { logout(); return; }

        System.out.println("\n--- SEEKER DASHBOARD (Welcome, " + seeker.getFullName() + ") ---");
        System.out.println("1. Manage Resume\n2. Search & Apply\n3. My Applications\n4. Notifications\n5. Logout");
        System.out.print("Choice: ");
        
        try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> manageResume(seeker.getId());
                case 2 -> searchAndApply(seeker.getId());
                case 3 -> viewMyApplications(seeker.getId());
                case 4 -> viewNotifications();
                case 5 -> logout();
            }
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void manageResume(int seekerId) {
        com.jobportal.dto.Resume r = resumeDao.getResumeByJobSeekerId(seekerId);
        if (r == null) {
            System.out.print("Enter Career Objective: ");
            String objective = sc.nextLine();
            Resume newResume = new Resume();
            newResume.setJobSeekerId(seekerId);
            newResume.setObjective(objective);
            if (resumeDao.addResume(newResume)) System.out.println("Resume created!");
        } else {
            System.out.println("Current Objective: " + r.getObjective());
            System.out.print("Enter New Objective (Enter to skip): ");
            String obj = sc.nextLine();
            if (!obj.trim().isEmpty()) {
                r.setObjective(obj);
                resumeDao.updateResume(r);
                System.out.println("Updated successfully!");
            }
        }
    }

    private static void searchAndApply(int seekerId) {
        com.jobportal.dto.Resume resume = resumeDao.getResumeByJobSeekerId(seekerId);
        if (resume == null) {
            System.out.println("[!] Create a resume (Option 1) first!");
            return;
        }

        List<Job> jobs = jobDao.getActiveJobs();
        if (jobs == null || jobs.isEmpty()) {
            System.out.println("No active jobs found.");
            return;
        }

        System.out.println("\n--- AVAILABLE JOBS ---");
        for (Job j : jobs) {
            System.out.println("[" + j.getId() + "] " + j.getTitle() + " - " + j.getLocation());
        }

        System.out.print("Enter Job ID to Apply (0 to cancel): ");
        try {
            int jobId = Integer.parseInt(sc.nextLine());
            if (jobId == 0) return;

            Application app = new Application();
            app.setJobId(jobId);
            app.setJobSeekerId(seekerId);
            app.setResumeId(resume.getId());
            app.setStatus("APPLIED");
            app.setAppliedDate(LocalDateTime.now());

            if (appDao.applyJob(app)) {
                System.out.println("Applied successfully!");
            }
        } catch (Exception e) { System.out.println("Invalid Job ID."); }
    }

    private static void viewMyApplications(int seekerId) {
        List<Application> list = appDao.getApplicationsByJobSeeker(seekerId);
        if (list == null || list.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }
        System.out.println("\n--- My Applications ---");
        for (Application a : list) {
            System.out.printf("Job ID: %d | Status: %s | Date: %s\n", a.getJobId(), a.getStatus(), a.getAppliedDate().toLocalDate());
        }
    }

    private static void viewNotifications() {
        List<Notification> notifications = notifDao.getNotificationsByUserId(currentUser.getId());
        if (notifications == null || notifications.isEmpty()) {
            System.out.println("No new notifications.");
            return;
        }
        for (Notification n : notifications) {
            System.out.println("[" + n.getCreatedAt().toLocalDate() + "] " + n.getMessage());
        }
    }

    private static void manageJobs(int companyId) {
        List<Job> myJobs = jobDao.getJobsByCompany(companyId);
        if (myJobs == null || myJobs.isEmpty()) {
            System.out.println("No jobs posted yet.");
            return;
        }
        System.out.println("\n--- YOUR JOBS ---");
        for (Job j : myJobs) {
            System.out.printf("[%d] %s | %s\n", j.getId(), j.getTitle(), j.getStatus());
        }
    }

    private static void viewApplicants(int employerId) {
        Company company = companyDao.getCompanyByEmployer(employerId);
        if (company == null) return;
        List<Job> myJobs = jobDao.getJobsByCompany(company.getId());
        if (myJobs == null || myJobs.isEmpty()) return;

        for (Job j : myJobs) System.out.println("ID: " + j.getId() + " | Title: " + j.getTitle());
        System.out.print("Enter Job ID to see applicants: ");
        try {
            int jobId = Integer.parseInt(sc.nextLine());
            List<Application> apps = appDao.getApplicationsByJob(jobId);
            if (apps == null || apps.isEmpty()) {
                System.out.println("No applicants.");
            } else {
                for (Application a : apps) {
                    System.out.printf("App ID: %d | Seeker: %d | Status: %s\n", a.getId(), a.getJobSeekerId(), a.getStatus());
                }
            }
        } catch (Exception e) { System.out.println("Error."); }
    }

    private static void logout() {
        System.out.println("Logging out...");
        currentUser = null;
    }
}