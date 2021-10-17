<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="value" required="true" rtexprvalue="true" type="by.training.cafeproject.domain.Person"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

${value.surname}&nbsp;${fn:substring(value.name, 0, 1)}.