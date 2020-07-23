package com.example.demo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.ImageDao;
import com.example.demo.model.Image;

@SpringBootApplication
public class ImageServiceApplication implements CommandLineRunner{
	private ImageDao imageDao;

	@Autowired
	public ImageServiceApplication(ImageDao imageDao) {
		super();
		this.imageDao = imageDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(ImageServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		imageDao.save(new Image(UUID.randomUUID(), "demourl-1") );
		imageDao.save(new Image(UUID.randomUUID(),"demourl-2"));
	}

}
