package br.com.SpringRestJWT.domain.converters;

import org.springframework.core.convert.converter.Converter;

import br.com.SpringRestJWT.domain.enums.Booleano;

public class BooleanoConverter implements Converter<String, Booleano> {

    @Override
    public Booleano convert(String source) {
        return Booleano.getByBoolean(Boolean.parseBoolean(source));
    }
}
