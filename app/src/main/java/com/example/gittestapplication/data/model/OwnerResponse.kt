package com.example.gittestapplication.data.model

import com.example.gittestapplication.domain.model.Owner

data class OwnerResponse(
    val id: Int?,
    val login: String?,
    val url: String?,
    val avatarUrl: String?,
    val eventsUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val gistsUrl: String?,
    val gravatarId: String?,
    val htmlUrl: String?,
    val nodeId: String?,
    val organizationsUrl: String?,
    val receivedEventsUrl: String?,
    val reposUrl: String?,
    val siteAdmin: Boolean?,
    val starredUrl: String?,
    val subscriptionsUrl: String?,
    val type: String?
)

fun OwnerResponse.toEntity(): Owner? {
    return Owner(
        id ?: return null,
        login ?: return null,
        url ?: return null,
        avatarUrl,
        eventsUrl,
        followersUrl,
        followingUrl,
        gistsUrl,
        gravatarId,
        htmlUrl,
        nodeId,
        organizationsUrl,
        receivedEventsUrl,
        reposUrl,
        siteAdmin,
        starredUrl,
        subscriptionsUrl,
        type
    )
}