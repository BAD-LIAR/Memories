package ru.itis.memories.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Memorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private String text;

    @Column(columnDefinition = "boolean  default false")
    private Boolean isDeleted;




    private static final long serialVersionUID = 7880619528188746225L;


    @OneToMany(mappedBy = "memorie")
    private List<MemorieAccess> participantMemories;



}
