package in.nareshit.raghu.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.apiclub.captcha.Captcha;
import in.nareshit.raghu.captcha.CaptchaUtils;
import in.nareshit.raghu.command.UserCommand;
import in.nareshit.raghu.dto.UserDTO;
import in.nareshit.raghu.modal.Student;
import in.nareshit.raghu.modal.User;
import in.nareshit.raghu.service.IStudentService;
import in.nareshit.raghu.service.LoginServiceMgmt;

@Controller
@RequestMapping()
public class StudentController {

	@Autowired
	private IStudentService service;
	@Autowired
	private LoginServiceMgmt Logservice;
	
	@GetMapping("/login.htm")
	public   String  showForm(@ModelAttribute("userCmd") UserCommand cmd) {
		return "login_form";
	}
	
	
	@PostMapping("/login.htm")
	public   String processForm(Map<String,Object> map,@ModelAttribute("user") User cmd) {
      String resultMsg=null;
      UserDTO dto=null;
      //convert Command to DTO
      dto=new UserDTO();
      BeanUtils.copyProperties(cmd, dto);
		//use service
      resultMsg=Logservice.authenticate(dto);
      map.put("result",resultMsg);
      return "show_result";
	}


	private void setupCaptcha(Student s) {
		Captcha captcha = CaptchaUtils.createCaptcha(200, 50);
		s.setHidden(captcha.getAnswer());
		s.setCaptcha("");
		s.setImage(CaptchaUtils.encodeBase64(captcha));
	}
	
	

	@GetMapping("/")
	public String showRegister(Model model) {
		Student s  = new Student();
		setupCaptcha(s);
		model.addAttribute("student", s);

		return "StudentRegister";
	}

	@PostMapping("/save")
	public String saveStudent(
			@ModelAttribute("student") Student student,
			Model model) 
	{
             System.out.println("add sop in save student method ");
		String page="";
		if(student.getCaptcha().equals(student.getHidden()))
		{
			service.createStudent(student);
			page ="redirect:all";
		} else {
			setupCaptcha(student);
			return "StudentRegister";
		}
		return page;
	}

	@GetMapping("/all")
	public String getAllStudent(Model model) 
	{
		model.addAttribute("list", service.getAllStudents());
		return "StudentData";
	}


	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable Integer id,Model model) 
	{
		String page = null;
		Optional<Student> opt = service.getOneStudent(id);
		if(opt.isPresent()) {
			Student s = opt.get(); 
			setupCaptcha(s);
			model.addAttribute("student", s);
			page = "StudentRegister";
		}else {
			page ="redirect:all";
		}

		return page;
	}
}
