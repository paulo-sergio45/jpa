package com.bookstore.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public record BookModelDto(String title, UUID publisherId, Set<UUID> authorIds,String reviewComment) {

}
