package com.phantomthieves.api.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Imagem;

@Controller
@RequestMapping("/imagem")
public class ImagemController {

	@GetMapping("/uploadMultiFile")
	public ModelAndView uploadMultiFileHandler() {
		ModelAndView resultado = new ModelAndView("imagem/uploadMultiFile");
		Imagem myUploadForm = new Imagem();
		resultado.addObject("myUploadForm", myUploadForm);

		return resultado;
	}

	// POST: Do Upload
	@PostMapping("/uploadMultiFile")
	public ModelAndView uploadMultiFileHandlerPOST(HttpServletRequest request, @ModelAttribute("myUploadForm") Imagem myUploadForm) {
		return this.doUpload(request, myUploadForm);

	}

	private ModelAndView doUpload(HttpServletRequest request, //
			Imagem myUploadForm) {
		ModelAndView resultado = new ModelAndView("imagem/uploadResult");
		String description = myUploadForm.getDescription();
		System.out.println("Description: " + description);

		// Root Directory.
		String uploadRootPath = "C:\\Users\\Fabio\\Desktop\\Nova pasta (2)";
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile[] fileDatas = myUploadForm.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();
		
		for (MultipartFile fileData : fileDatas) {

			// Client File Name
			String name = fileData.getOriginalFilename();
			//Exemplo de como alterar o nome do produto
			String novo = "02" + name;
			System.out.println("Client File Name = " + novo);
		
			if (name != null && name.length() > 0) {
				try {
					// Create the file at server
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + novo);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
					System.out.println("Write file: " + serverFile);
					System.out.println("Teste do quero ver: " + uploadRootDir);
				} catch (Exception e) {
					System.out.println("Error Write file: " + novo);
					failedFiles.add(novo);
				}
			}
		}
		resultado.addObject("description", description);
		resultado.addObject("uploadedFiles", uploadedFiles);
		resultado.addObject("failedFiles", failedFiles);
		return resultado;
	}

}
