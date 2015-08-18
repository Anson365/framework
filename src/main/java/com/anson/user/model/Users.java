package com.anson.user.model;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String username;

    private String phonenumber;

    private String password;

    private Date createdat;
    
    public Users() {
		super();
	}
    

    public Users(Long id, String username, String phonenumber, String password, Date createdat) {
		super();
		this.id = id;
		this.username = username;
		this.phonenumber = phonenumber;
		this.password = password;
		this.createdat = createdat;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }
}