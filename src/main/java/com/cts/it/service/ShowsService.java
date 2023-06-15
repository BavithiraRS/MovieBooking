package com.cts.it.service;

import java.util.List;

import com.cts.it.model.Shows;
import com.cts.it.model.Theatre;

public interface ShowsService {
	
	 List<Shows> getAllShows();

	 Shows getShowsByShow_id(int show_id);

	 Shows pushShows(Shows newShow);

	 Shows updateShows(Shows updatedShow, int show_id);

	 void deleteShowsById(int show_id);


}
