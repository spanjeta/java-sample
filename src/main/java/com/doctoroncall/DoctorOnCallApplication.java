package com.doctoroncall;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.doctoroncall.entities.Activity;
import com.doctoroncall.entities.Appointment;
import com.doctoroncall.entities.Category;
import com.doctoroncall.entities.CityFee;
import com.doctoroncall.entities.Disorder;
import com.doctoroncall.entities.Doctor;
import com.doctoroncall.entities.Patient;
import com.doctoroncall.entities.Role;
import com.doctoroncall.entities.SubCategory;
import com.doctoroncall.entities.Ticket;
import com.doctoroncall.entities.TimeSlot;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.ActivityRepository;
import com.doctoroncall.repository.CityFeeRepository;
import com.doctoroncall.repository.DoctorRepository;
import com.doctoroncall.repository.PatientRepository;
import com.doctoroncall.repository.RoleRepository;
import com.doctoroncall.repository.TicketRepository;
import com.doctoroncall.repository.TimeSlotRepository;
import com.doctoroncall.services.AppointmentService;
import com.doctoroncall.services.CategoryService;
import com.doctoroncall.services.DisorderService;
import com.doctoroncall.services.SubCategoryService;
import com.doctoroncall.services.UserService;

@SpringBootApplication
@EnableScheduling
public class DoctorOnCallApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DoctorOnCallApplication.class, args);
	}

	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private CityFeeRepository cityFeeRepository;



	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DisorderService disorderService;
	@Autowired
	private SubCategoryService subCategoryService;
	@Autowired
	private AppointmentService appointmentService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		{
			if (userService.count() == 0) {
				User newAdmin = new User("admin@toxsl.in", "Admin", "admin@123", "9998887771");

				userService.createAdmin(newAdmin);

				Role moderatorRole = new Role("MODERATOR");
				roleRepository.save(moderatorRole);

				Role doctorRole = new Role("DOCTOR");
				roleRepository.save(doctorRole);

				Role patientRole = new Role("PATIENT");
				roleRepository.save(patientRole);

				User newmoderator = new User("moderator@toxsl.in", "Shiv Moderator", "admin@123", "9876543210");

				userService.createUser(newmoderator, moderatorRole);

				User newDoctor = new User("doctor@toxsl.in", "Manish Doctor", "admin@123", "9671930095");
				userService.createUser(newDoctor, doctorRole);

				Doctor doctorProfile  = new Doctor(newDoctor, "3 year", "diagnostic");
				doctorRepository.save(doctorProfile);

				User newPatient = new User("patient@toxsl.in", "Dev Patient", "admin@123", "8527469123");
				userService.createUser(newPatient, patientRole);

				Patient patientProfile = new Patient(newPatient, "1", "123456", "m.jpg", "mac");
				patientRepository.save(patientProfile);

				Ticket ticket = new Ticket(newPatient, "Website Problem ", "Website Is not working properly ");
				ticketRepository.save(ticket);

				Ticket ticket2 = new Ticket(newPatient, "Doctor is Not Responding",
						"Doctor is not responding whenever I call him");
				ticketRepository.save(ticket2);

				Ticket ticket3 = new Ticket(newPatient, "Call disConnecting",
						"Network Problems arises Mostle during Calling");
				ticketRepository.save(ticket3);

				Category category = new Category("walking");
				categoryService.save(category);

				Activity activity = new Activity("exersice", category, newDoctor, newPatient);
				activityRepository.save(activity);

				CityFee mohaliFee = new CityFee("Mohali", "200123"," 250");
				cityFeeRepository.save(mohaliFee);

				Date dt = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dt);

				Integer index = 0;
				for (index = 0; index < 10; index++) {

					TimeSlot timeSlot = new TimeSlot(c.getTime(), newDoctor);
					timeSlotRepository.save(timeSlot);
					Appointment appointment = new Appointment(newDoctor, newPatient, c.getTime());
					appointmentService.add(appointment);
					c.add(Calendar.DATE, 1);
				}
				

				for (index = 0; index < 25; index++) {

					Category cat = new Category("Category" + index);
					categoryService.save(cat);
					SubCategory subcat = new SubCategory("subCategory" + index);
					subCategoryService.save(subcat);
					
				}
				for (index = 0; index < 10; index++) {

					Disorder disorder = new Disorder("disorder" + index);
					disorderService.save(disorder);
				}	
					
				

			}

		}

	}
}
