package com.bae.personalprojectstarships.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Starship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // <- Type of id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;
	private String model;
	private int age;

	public Starship(String name, String model, int age) {
		this.name = name;
		this.model = model;
		this.age = age;
	}

	public Starship(Long id, String name, String model, int age) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.age = age;
	}

	public Starship() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
