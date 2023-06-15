package com.cts.it.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "theatre_id")
    private int theatre_id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "location")
    private String location;
    
    @OneToMany(mappedBy="theatre")
    @JsonBackReference
	@JsonIgnore
    private List<Shows> show;

	

//	public Theatre(int i, String string) {
//		// TODO Auto-generated constructor stub
//	}

	public int getTheatre_id() {
		return theatre_id;
	}

	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Shows> getShow() {
		return show;
	}

	public void setShow(List<Shows> show) {
		this.show = show;
	}

	
}
