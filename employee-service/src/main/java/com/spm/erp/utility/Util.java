package com.spm.erp.utility;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.lang.reflect.Field;
import com.spm.erp.model.Employee;

public class Util {

	public static Employee updateProperties(Employee newEmp, Employee oldEmp) {
		if (oldEmp == null || newEmp == null) {
			return null;
		}

		BeanWrapper oldObj = new BeanWrapperImpl(oldEmp);
		BeanWrapper newObj = new BeanWrapperImpl(newEmp);

		for (Field property : newEmp.getClass().getDeclaredFields()) {
			Object emp = oldObj.getPropertyValue(property.getName());
			if (emp != null) {
				newObj.setPropertyValue(property.getName(), emp);
			}
		}
		return newEmp;
	}
}
