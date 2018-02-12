package com.strong.model.entity;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import lombok.ToString;
//import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author cy
 */
//@Accessors(chain = true)
//@NoArgsConstructor
//@Getter
//@Setter
@ToString
public class BookStore implements Serializable {

    private static final long serialVersionUID = 1183385713216587274L;

    private long id;
    private String name;
    private String address;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
