package com.dbalthassat.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.LinkedList;
import java.util.List;

public class PersonDTO {
	private Long id;
	private String name;
	private Boolean hasFriend;
	private String friend;
    private List<String> peopleToAvoid = new LinkedList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getHasFriend() {
		return hasFriend;
	}

	public void setHasFriend(Boolean hasFriend) {
		this.hasFriend = hasFriend;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

    public List<String> getPeopleToAvoid() {
        return peopleToAvoid;
    }

    public void setPeopleToAvoid(List<String> peopleToAvoid) {
        this.peopleToAvoid = peopleToAvoid;
    }
}
