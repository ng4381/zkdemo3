package org.example.sidepage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class SidebarPage {
    String name;
    String label;
    String iconUri;
    String uri;
}
