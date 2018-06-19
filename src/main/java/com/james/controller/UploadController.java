package com.james.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@PostMapping("/upload")
	public String test(@RequestParam("img")MultipartFile img,HttpServletRequest request){
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("price"));
		
		String path = request.getRealPath("/upload/");
		
		String filename = img.getOriginalFilename();
		
		String location = path + filename;
		
		File pathFile = new File(path);
		
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		
		System.out.println(location);
		
		File f = new File(location);
		
		try {
			f.createNewFile();
			
			img.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
}
