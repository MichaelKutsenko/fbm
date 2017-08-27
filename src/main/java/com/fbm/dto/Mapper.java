package com.fbm.dto;

/**
 * Maps internal objects to external dto objects
 * @param <InternalObject> An object, that represents an internal entity
 * @param <ExternalObject> An object, that represent dto data
 */
public interface Mapper<InternalObject, ExternalObject> {

    ExternalObject convertToDto(InternalObject convertFrom);

    InternalObject convertToNewEntity(ExternalObject convertFrom);

    InternalObject convertToExistEntity(ExternalObject convertFrom, InternalObject covertTo);
}
