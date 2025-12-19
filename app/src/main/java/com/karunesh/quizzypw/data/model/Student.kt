package com.karunesh.quizzypw.data.model

data class Student(
    val name: String,
    val `class`: String,
    val availability: Availability,
    val quiz: Quiz,
    val accuracy: Accuracy
)
