package com.cts.it.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cts.it.model.Shows;
import com.cts.it.repository.ShowsRepository;
import com.cts.it.service.ShowsService;

@Service
@Primary
public class ShowsServiceImpl implements ShowsService{
	@Autowired
	private ShowsRepository sRepo;
	
	public ShowsServiceImpl(ShowsRepository sRepo) {
		this.sRepo=sRepo;
	}

	@Override
	public List<Shows> getAllShows() {
		// TODO Auto-generated method stub
		return sRepo.findAll();
	}

	

	@Override
	public Shows pushShows(Shows newShow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shows updateShows(Shows updatedShow, int show_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShowsById(int show_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shows getShowsByShow_id(int show_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
