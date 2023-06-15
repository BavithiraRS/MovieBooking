package com.cts.it.service.impl;

import com.cts.it.model.Theatre;
import com.cts.it.repository.TheatreRepository;
import com.cts.it.service.TheatreService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TheatreServiceImpl implements TheatreService {
    private TheatreRepository theatreRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }


    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(int theatre_id) {
        return null;
    }

    @Override
    public Theatre pushTheatre(Theatre newTheatre) {
        return null;
    }

    @Override
    public Theatre updateTheatre(Theatre updatedTheatre, int theatre_id) {
        return null;
    }

    @Override
    public void deleteTheatreById(int theatre_id) {
    	theatreRepository.deleteById(theatre_id);
    }

}
