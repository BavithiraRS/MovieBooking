package com.cts.it.service;

import com.cts.it.model.Theatre;

import java.util.List;

public interface TheatreService {
    List<Theatre> getAllTheatres();

    Theatre getTheatreById(int theatre_id);

    Theatre pushTheatre(Theatre newTheatre);

    Theatre updateTheatre(Theatre updatedTheatre, int theatre_id);

    void deleteTheatreById(int theatre_id);
}
