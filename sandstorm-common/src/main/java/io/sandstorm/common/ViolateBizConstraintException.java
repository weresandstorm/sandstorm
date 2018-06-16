package io.sandstorm.common;

public class ViolateBizConstraintException extends ApplicationException {

    public ViolateBizConstraintException(String message) {
        super(new ResultCase(CaseCode.violate_biz_constraint, message));
    }

    public ViolateBizConstraintException(CaseCode caseCode, String message) {
        super(new ResultCase(caseCode, message));
    }

}
