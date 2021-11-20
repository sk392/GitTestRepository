package com.example.gittestapplication.domain.model

enum class Sort(name: String) {
    STARS("stars"), FORKS("forks"), WANTED_ISSUES("help-wanted-issues"), UPDATED("updated"), DEFAULT("best-match")
}

enum class Order(name: String) {
    DESC("desc"), ASC("asc")
}