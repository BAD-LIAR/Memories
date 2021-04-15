package ru.itis.memories.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.User;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemorieDto {

    private Long id;
    private User owner;
    private String text;


    public static MemorieDto from(Memorie memorie) {
        if (memorie == null) {
            return null;
        }
        return MemorieDto.builder()
                .id(memorie.getId())
                .owner(memorie.getOwner())
                .text(memorie.getText())
                .build();
    }

    public static List<MemorieDto> from(List<Memorie> memories) {
        return memories.stream()
                .map(MemorieDto::from)
                .collect(Collectors.toList());
    }




}
