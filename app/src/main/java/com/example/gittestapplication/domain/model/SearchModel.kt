package com.example.gittestapplication.domain.model

enum class Sort(val queryName: String) {
    STARS("stars"), FORKS("forks"), WANTED_ISSUES("help-wanted-issues"), UPDATED("updated"), DEFAULT("best-match")
}

enum class Order(val queryName: String) {
    DESC("desc"), ASC("asc")
}