package com.alura.foro.course;

import jakarta.validation.constraints.NotNull;

public record UpdatingDataCourse(@NotNull Long id,String nombre, Categories categoria) {

}
