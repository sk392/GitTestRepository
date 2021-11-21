package com.example.gittestapplication.domain.model

data class Owner(
    val id: Int,
    val login: String = "name $id",
    val url: String = "https://www.github.com/$id",
    val avatarUrl: String?= null,
    val eventsUrl: String?= null,
    val followersUrl: String?= null,
    val followingUrl: String?= null,
    val gistsUrl: String?= null,
    val gravatarId: String?= null,
    val htmlUrl: String?= null,
    val nodeId: String?= null,
    val organizationsUrl: String?= null,
    val receivedEventsUrl: String?= null,
    val reposUrl: String?= null,
    val siteAdmin: Boolean?= null,
    val starredUrl: String?= null,
    val subscriptionsUrl: String?= null,
    val type: String?= null
)