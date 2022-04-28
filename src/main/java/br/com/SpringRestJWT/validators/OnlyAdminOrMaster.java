package br.com.SpringRestJWT.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@authService.temPermissao(authentication, 'isAdmin', true) or @authService.temPermissao(authentication, 'isMaster', true)")
public @interface OnlyAdminOrMaster {}
