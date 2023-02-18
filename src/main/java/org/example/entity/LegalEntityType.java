package org.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LegalEntityType {
    OOO("ООО"),
    IP("ИП"),
    AO("АО");

    private final String typeName;

    @Override
    public String toString() {
        return typeName;
    }
}
