package com.strong.model.entity;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import lombok.ToString;
//import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author cy
 */
//@Accessors(chain = true)
//@NoArgsConstructor
//@Getter
//@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 7698862379923111158L;

    private String id;
    private String username;
    private String password;
    private String status;    
    private String role;
    private Timestamp createDate;    
    private String email;    
    private String gender;   
    private String signature;
    private Set<UserAuthority> authorities = new LinkedHashSet<UserAuthority>();
    
    public User() {
		super();
	}
    public User(String userName, String password, String email, String gender, String signature) {
		super();
		this.username = userName;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.signature = signature;
	}    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}
