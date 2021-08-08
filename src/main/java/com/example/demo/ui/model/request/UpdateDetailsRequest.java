package com.example.demo.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateDetailsRequest {

	@NotNull(message = "Not null")
	@Size(min = 3, max = 10)
	private String name;

	@NotNull(message = "Not null")
	@Size(min = 3, max = 10)
	private String lastName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
