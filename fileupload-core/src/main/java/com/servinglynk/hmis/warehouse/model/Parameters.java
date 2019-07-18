package com.servinglynk.hmis.warehouse.model;

import java.util.ArrayList;




	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public void addParameter(Parameter parameter) {
		if (parameters == null) {
			this.parameters = new ArrayList<Parameter>();
		}
		parameters.add(parameter);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}