package com.junjie.controller;

import org.springframework.validation.Errors;
import com.junjie.model.Timesheet;

/**
 * Validator for EnterHoursController
 * @author anil
 * @see com.junjie.controller.EnterHoursController
 */
public class EnterHoursValidator implements
        org.springframework.validation.Validator
{
    public boolean supports(Class clazz)
    {
        return clazz.equals(Timesheet.class);
    }

    /**
     * Validates the Timesheet command object.  Ensures that the
     * departmentCode is specified.
     * @see Timesheet 
     */
    public void validate(Object command, Errors errors)
    {
        Timesheet timesheet = (Timesheet) command;
        if (timesheet == null) return;

        if (timesheet.getDepartmentCode() == null
                || timesheet.getDepartmentCode().trim().length() < 1)
            errors.reject("error.enterhours.missingdepartment");
    }
}
