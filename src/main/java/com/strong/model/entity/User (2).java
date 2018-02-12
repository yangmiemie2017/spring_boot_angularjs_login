//package com.strong.entity;
//
//import java.sql.Timestamp;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.ConstraintMode;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Table(name = "T_USER", schema = "APP")
//public class User {
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
//    @Column(name = "ID", columnDefinition = "CHAR(36)")
//    private String id;
//
//    @Column(name = "USER_NAME", length = 50)
//    private String userName;
//
//    @Column(name = "STATUS", columnDefinition = "CHAR(1)")
//    private String status;
//
//    @Column(name = "PASSWORD", length = 50)
//    private String password;
//
//    @Column(name = "ROLE", columnDefinition = "CHAR(1)")
//    private String role;
//
//    @Column(name = "CREATE_DATE")
//    private Timestamp createDate;
//    
//    @Column(name = "EMAIL")
//    private String email;
//    
//    @Column(name = "GENDER")
//    private String gender;
//    
//    @Column(name = "SIGNATURE")
//    private String signature;
//    
//    public User() {
//		super();
//	}
//
//
//    public User(String userName, String password, String email, String gender, String signature) {
//		super();
//		this.userName = userName;
//		this.password = password;
//		this.email = email;
//		this.gender = gender;
//		this.signature = signature;
//	}
//
//    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
//    @JoinTable(schema = "APP", name = "T_USER_ROLE", 
//        joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "id", foreignKey = @ForeignKey(value= ConstraintMode.NO_CONSTRAINT))}, 
//        inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "id", foreignKey = @ForeignKey(value= ConstraintMode.NO_CONSTRAINT))})
//    private Set<UserAuthority> authorities = new LinkedHashSet<UserAuthority>();
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Timestamp getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Timestamp createDate) {
//        this.createDate = createDate;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<UserAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<UserAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getSignature() {
//		return signature;
//	}
//
//	public void setSignature(String signature) {
//		this.signature = signature;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//}
