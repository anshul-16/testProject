package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.dao.ImageDao;
import com.example.demo.model.Image;
@Service
@EnableTransactionManagement
public class ImageServiceImpl implements ImageService{
	private ImageDao imageDao;

	@Autowired
	public ImageServiceImpl(ImageDao imageDao) {
		super();
		this.imageDao = imageDao;
	}

	@Override
	@Transactional
	public Iterable<Image> getAllImage() {
		// TODO Auto-generated method stub
		return imageDao.findAll();
	}

}
