package com.dbalthassat.entity.listener;

import com.dbalthassat.entity.Slugable;
import com.dbalthassat.utils.SlugUtils;

import javax.persistence.PrePersist;

public class SlugEntityListener {
	@PrePersist
	public void populateSlug(Slugable entity) {
		entity.setSlug(SlugUtils.createSlug(entity.itemToSlug()));
	}
}
