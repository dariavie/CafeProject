function validateEditFood(form) {
    return validateForm(form, [{
        id:"login",
        message: "Поле «Login» не заполнено",
        checker: checkNotEmpty
    }, {
        id:"password",
        message: "Поле «Password» не заполнено",
        checker: checkNotEmpty
    }, {
        id: "surname",
        message: "Поле «Фамилия» не заполнено",
        checker: checkNotEmpty
    }, {
        id: "name",
        message: "Поле «Имя» не заполнено",
        checker: checkNotEmpty
    }, {
        id: "phone",
        message: "Поле «Телефон» должно быть задано в одном из следующих форматов:<BR>+@@# (##) ###-##-##<BR>+@@# (###) ##-##-##<BR>+@@# (####) #-##-##<BR>(# - обязательная цифра, @ - необязательная цифра)",
        checker: checkPhone
    }, {
        id: "email",
        message: "Поле «email» не заполнено",
        checker: checkNotEmpty
    }]);
}

function checkPhone(value) {
    return checkRegexp(value, "^\\+\\d{1,3} \\(((\\d{2}\\) \\d{3})|(\\d{3}\\) \\d{2})|(\\d{4}\\) \\d))-\\d{2}-\\d{2}$");
}