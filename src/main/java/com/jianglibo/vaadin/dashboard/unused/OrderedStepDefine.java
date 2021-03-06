package com.jianglibo.vaadin.dashboard.unused;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.jianglibo.vaadin.dashboard.annotation.VaadinTable;
import com.jianglibo.vaadin.dashboard.annotation.VaadinTableColumn;
import com.jianglibo.vaadin.dashboard.domain.BaseEntity;
import com.jianglibo.vaadin.dashboard.domain.HasPositionField;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
//@Entity
//@VaadinTable(multiSelect = true, messagePrefix = "domain.orderedstepdefine.", footerVisible = true, styleNames = {
//		ValoTheme.TABLE_BORDERLESS, ValoTheme.TABLE_NO_HORIZONTAL_LINES,
//		ValoTheme.TABLE_COMPACT }, selectable = true, fullSize = true)
//@Table(name = "orderedstepdefine")
public class OrderedStepDefine extends BaseEntity implements HasPositionField {
	
	@OneToOne
	@VaadinTableColumn(order = 20)
	private StepDefine stepDefine;
	
	@VaadinTableColumn(order = 10)
	private int position;
	
	
	public OrderedStepDefine() {
	}
	
	public OrderedStepDefine(StepDefine stepDefine, int position) {
		this.stepDefine = stepDefine;
		this.position = position;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("position", getPosition()).add("stepDefine", getStepDefine()).toString();
	}


	@Override
	public String getDisplayName() {
		return String.format("[%s,%s]", getStepDefine().getName(), getStepDefine().getOstype());
	}

	public StepDefine getStepDefine() {
		return stepDefine;
	}

	public void setStepDefine(StepDefine stepDefine) {
		this.stepDefine = stepDefine;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
