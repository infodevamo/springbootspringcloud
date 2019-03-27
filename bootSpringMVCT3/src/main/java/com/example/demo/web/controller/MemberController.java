package com.example.demo.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.repository.IMemberRepository;
import com.example.demo.repository.Member;
import com.example.demo.web.dto.UserDto;


@Controller
@RequestMapping("/web")
@SessionAttributes("loggedUser")
public class MemberController {

	@Autowired
	IMemberRepository memberRepository;
	
	@GetMapping("/login")
	public String goLoggin(Model model) {
		model.addAttribute("user", new UserDto());
		return "login";
	}
	
	@PostMapping("/authenticate")
	public String authenticate(@Valid @ModelAttribute(name="user") UserDto userDto, BindingResult bindingResult, Model model) {
		boolean isAuthenticate = true;
		if(bindingResult.hasErrors()) {
			
		}
		if(isAuthenticate) {
			List<Member> members = memberRepository.findByEmail(userDto.getEmail());
			Member member = memberRepository.fullLoad(members.get(0).getId());
			model.addAttribute("loggedUser", member);
			return "redirect:documents";
		} else {
			return "register";
		}
	}
}
