package com.phantomthieves.api.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Imagem;
import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.model.Upload;
import com.phantomthieves.api.repository.ImagemRepository;
import com.phantomthieves.api.repository.ProdutoRepository;

@Controller
@RequestMapping("imagem")
public class UploadController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagemRepository imagemRepository;
	
	@GetMapping("/uploadMultiFile")
	public ModelAndView uploadMultiFileHandler() {
		ModelAndView resultado = new ModelAndView("imagem/uploadMultiFile");
		Upload myUploadForm = new Upload();
		resultado.addObject("myUploadForm", myUploadForm);

		return resultado;
	}

	// POST: Do Upload
	@PostMapping("/uploadMultiFile")
	public ModelAndView uploadMultiFileHandlerPOST(HttpServletRequest request, @ModelAttribute("myUploadForm") Upload myUploadForm) {
		return this.doUpload(request, myUploadForm);

	}

	private ModelAndView doUpload(HttpServletRequest request, //
			Upload myUploadForm) {
		
		Produto produto = new Produto();
		produto = produtoRepository.findByLast();
		Integer idProduto = produto.getId();
		
		String name = "";
		
		ModelAndView resultado = new ModelAndView("imagem/uploadResult");
		String description = myUploadForm.getDescription();
		System.out.println("Description: " + description);

		// Root Directory.
		String uploadRootPath = "C:\\Users\\fabio.msilva21\\Desktop\\tet";
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
		List<Imagem> img = new ArrayList<Imagem>();
		for (MultipartFile fileData : fileDatas) {

			// Client File Name
			name = idProduto +fileData.getOriginalFilename();
			img.add(new Imagem(name, uploadRootPath));
		
			if (name != null && name.length() > 0) {
				try {
					// Create the file at server
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
					
					System.out.println("Write file: " + serverFile);
					System.out.println("Teste do quero ver: " + uploadRootDir);
				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
					failedFiles.add(name);
				}
			}
		}
		for(Imagem im : img) {
			produto.addImagem(im);
		}
		produtoRepository.save(produto);
	
		

		
		resultado.addObject("description", description);
		resultado.addObject("uploadedFiles", uploadedFiles);
		resultado.addObject("failedFiles", failedFiles);
		return resultado;
	}

}
	